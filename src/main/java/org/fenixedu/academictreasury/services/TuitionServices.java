package org.fenixedu.academictreasury.services;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.fenixedu.academic.domain.Enrolment;
import org.fenixedu.academic.domain.EnrolmentEvaluation;
import org.fenixedu.academic.domain.ExecutionYear;
import org.fenixedu.academic.domain.Person;
import org.fenixedu.academic.domain.student.Registration;
import org.fenixedu.academic.domain.student.RegistrationDataByExecutionYear;
import org.fenixedu.academictreasury.domain.customer.PersonCustomer;
import org.fenixedu.academictreasury.domain.event.AcademicTreasuryEvent;
import org.fenixedu.academictreasury.domain.exceptions.AcademicTreasuryDomainException;
import org.fenixedu.academictreasury.domain.settings.AcademicTreasurySettings;
import org.fenixedu.academictreasury.domain.tariff.AcademicTariff;
import org.fenixedu.academictreasury.domain.tuition.TuitionInstallmentTariff;
import org.fenixedu.academictreasury.domain.tuition.TuitionPaymentPlan;
import org.fenixedu.academictreasury.domain.tuition.TuitionPaymentPlanGroup;
import org.fenixedu.academictreasury.dto.tuition.TuitionDebitEntryBean;
import org.fenixedu.commons.i18n.LocalizedString;
import org.fenixedu.treasury.domain.Vat;
import org.fenixedu.treasury.domain.debt.DebtAccount;
import org.fenixedu.treasury.domain.document.CreditEntry;
import org.fenixedu.treasury.domain.document.CreditNote;
import org.fenixedu.treasury.domain.document.DebitEntry;
import org.fenixedu.treasury.domain.document.DebitNote;
import org.fenixedu.treasury.domain.document.DocumentNumberSeries;
import org.fenixedu.treasury.domain.document.FinantialDocumentType;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import pt.ist.fenixframework.Atomic;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class TuitionServices {

    public static boolean isToPayRegistrationTuition(final Registration registration, final ExecutionYear executionYear) {
        return registration.getRegistrationProtocol().isToPayGratuity();
    }

    public static AcademicTreasuryEvent findAcademicTreasuryEventTuitionForRegistration(final Registration registration,
            final ExecutionYear executionYear) {
        return AcademicTreasuryEvent.findUniqueForRegistrationTuition(registration, executionYear).orElse(null);
    }

    @Atomic
    public static boolean createInferedTuitionForRegistration(final Registration registration, final ExecutionYear executionYear,
            final LocalDate when, final boolean forceCreationIfNotEnrolled) {
        return createTuitionForRegistration(registration, executionYear, when, forceCreationIfNotEnrolled, null);
    }

    @Atomic
    public static boolean createTuitionForRegistration(final Registration registration, final ExecutionYear executionYear,
            final LocalDate when, final boolean forceCreationIfNotEnrolled, TuitionPaymentPlan tuitionPaymentPlan) {

        final Person person = registration.getPerson();
        // Read person customer

        if (!PersonCustomer.findUnique(person).isPresent()) {
            PersonCustomer.create(person);
        }

        final PersonCustomer personCustomer = PersonCustomer.findUnique(person).get();

        if (tuitionPaymentPlan == null) {
            tuitionPaymentPlan = TuitionPaymentPlan.inferTuitionPaymentPlanForRegistration(registration, executionYear);
        }

        if (tuitionPaymentPlan == null) {
            return false;
        }

        if (!forceCreationIfNotEnrolled && tuitionPaymentPlan.isStudentMustBeEnrolled()
                && normalEnrolments(registration, executionYear).isEmpty()) {
            return false;
        }

        if (!DebtAccount.findUnique(tuitionPaymentPlan.getFinantialEntity().getFinantialInstitution(), personCustomer)
                .isPresent()) {
            DebtAccount.create(tuitionPaymentPlan.getFinantialEntity().getFinantialInstitution(), personCustomer);
        }

        final DebtAccount debtAccount =
                DebtAccount.findUnique(tuitionPaymentPlan.getFinantialEntity().getFinantialInstitution(), personCustomer).get();

        if (!AcademicTreasuryEvent.findUniqueForRegistrationTuition(registration, executionYear).isPresent()) {
            AcademicTreasuryEvent.createForRegistrationTuition(debtAccount, tuitionPaymentPlan.getProduct(), registration,
                    executionYear);
        }

        final AcademicTreasuryEvent academicTreasuryEvent =
                AcademicTreasuryEvent.findUniqueForRegistrationTuition(registration, executionYear).get();

        return tuitionPaymentPlan.createDebitEntriesForRegistration(academicTreasuryEvent, when);
    }

    public static TuitionPaymentPlan usedPaymentPlan(final Registration registration, final ExecutionYear executionYear,
            final LocalDate debtDate) {
        return usedPaymentPlan(registration, executionYear, debtDate, null);
    }

    public static TuitionPaymentPlan usedPaymentPlan(final Registration registration, final ExecutionYear executionYear,
            final LocalDate debtDate, final TuitionPaymentPlan tuitionPaymentPlan) {
        if (tuitionPaymentPlan != null) {
            return tuitionPaymentPlan;
        }

        return TuitionPaymentPlan.inferTuitionPaymentPlanForRegistration(registration, executionYear);
    }

    @Atomic
    public static List<TuitionDebitEntryBean> calculateInstallmentDebitEntryBeans(final Registration registration,
            final ExecutionYear executionYear, final LocalDate debtDate) {
        return calculateInstallmentDebitEntryBeans(registration, executionYear, debtDate, null);
    }

    @Atomic
    public static List<TuitionDebitEntryBean> calculateInstallmentDebitEntryBeans(final Registration registration,
            final ExecutionYear executionYear, final LocalDate debtDate, TuitionPaymentPlan tuitionPaymentPlan) {

        final Person person = registration.getPerson();
        // Read person customer
        PersonCustomer personCustomer = PersonCustomer.findUnique(person).orElse(null);

        if (personCustomer == null) {
            personCustomer = PersonCustomer.create(person);
        }

        if (tuitionPaymentPlan == null) {
            tuitionPaymentPlan = TuitionPaymentPlan.inferTuitionPaymentPlanForRegistration(registration, executionYear);
        }

        if (tuitionPaymentPlan == null) {
            return Lists.newArrayList();
        }

        if (!DebtAccount.findUnique(tuitionPaymentPlan.getFinantialEntity().getFinantialInstitution(), personCustomer)
                .isPresent()) {
            DebtAccount.create(tuitionPaymentPlan.getFinantialEntity().getFinantialInstitution(), personCustomer);
        }

        final DebtAccount debtAccount =
                DebtAccount.findUnique(tuitionPaymentPlan.getFinantialEntity().getFinantialInstitution(), personCustomer).get();

        if (!AcademicTreasuryEvent.findUniqueForRegistrationTuition(registration, executionYear).isPresent()) {
            AcademicTreasuryEvent.createForRegistrationTuition(debtAccount, tuitionPaymentPlan.getProduct(), registration,
                    executionYear);
        }

        final AcademicTreasuryEvent academicTreasuryEvent =
                AcademicTreasuryEvent.findUniqueForRegistrationTuition(registration, executionYear).get();

        final List<TuitionDebitEntryBean> entries = Lists.newArrayList();
        for (final TuitionInstallmentTariff tuitionInstallmentTariff : tuitionPaymentPlan.getTuitionInstallmentTariffsSet()) {
            final int installmentOrder = tuitionInstallmentTariff.getInstallmentOrder();
            final LocalizedString installmentName = tuitionPaymentPlan.installmentName(tuitionInstallmentTariff);
            final LocalDate dueDate = tuitionInstallmentTariff.dueDate(debtDate);
            final Vat vat = tuitionInstallmentTariff.vat(debtDate);
            final BigDecimal amount = tuitionInstallmentTariff.amountToPay(academicTreasuryEvent);

            entries.add(new TuitionDebitEntryBean(installmentOrder, installmentName, dueDate, vat.getTaxRate(), amount));
        }

        return entries.stream().sorted(new Comparator<TuitionDebitEntryBean>() {

            @Override
            public int compare(TuitionDebitEntryBean o1, TuitionDebitEntryBean o2) {
                return o1.getInstallmentOrder() - o2.getInstallmentOrder();
            }

        }).collect(Collectors.toList());
    }

    /* **********
     * Standalone 
     * **********
     */

    public static AcademicTreasuryEvent findAcademicTreasuryEventTuitionForStandalone(final Registration registration,
            final ExecutionYear executionYear) {
        return AcademicTreasuryEvent.findUniqueForStandaloneTuition(registration, executionYear).orElse(null);
    }

    @Atomic
    public static boolean createInferedTuitionForStandalone(final Enrolment standaloneEnrolment, final LocalDate when) {
        return createInferedTuitionForStandalone(Sets.newHashSet(standaloneEnrolment), when);
    }

    @Atomic
    public static boolean createInferedTuitionForStandalone(final Set<Enrolment> standaloneEnrolments, final LocalDate when) {

        if(AcademicTreasurySettings.getInstance().getTuitionProductGroup() == null) {
            return false;
        }
        
        if(!TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone().isPresent()) {
            return false;
        }
        
        boolean created = false;

        // Validate all enrolments are standalone

        for (final Enrolment standaloneEnrolment : standaloneEnrolments) {
            if (!standaloneEnrolment.isStandalone()) {
                throw new AcademicTreasuryDomainException("error.TuitionServices.enrolment.is.not.standalone");
            }
        }

        for (final Enrolment standaloneEnrolment : standaloneEnrolments) {
            final Registration registration = standaloneEnrolment.getRegistration();

            final Person person = registration.getPerson();
            // Read person customer

            if (!PersonCustomer.findUnique(person).isPresent()) {
                PersonCustomer.create(person);
            }

            final PersonCustomer personCustomer = PersonCustomer.findUnique(person).get();

            final ExecutionYear executionYear = standaloneEnrolment.getExecutionYear();

            if (TuitionPaymentPlan
                    .inferTuitionPaymentPlanForStandaloneEnrolment(registration, executionYear, standaloneEnrolment) == null) {
                continue;
            }

            final TuitionPaymentPlan tuitionPaymentPlan =
                    TuitionPaymentPlan.inferTuitionPaymentPlanForStandaloneEnrolment(registration, executionYear,
                            standaloneEnrolment);

            created |= createTuitionForStandalone(standaloneEnrolment, tuitionPaymentPlan, when);
        }
        
        return created;
    }

    @Atomic
    public static boolean createTuitionForStandalone(final Enrolment standaloneEnrolment,
            final TuitionPaymentPlan tuitionPaymentPlan, final LocalDate when) {
        
        if(AcademicTreasurySettings.getInstance().getTuitionProductGroup() == null) {
            return false;
        }
        
        if(!TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone().isPresent()) {
            return false;
        }
        
        final Registration registration = standaloneEnrolment.getRegistration();
        final Person person = registration.getPerson();
        final ExecutionYear executionYear = standaloneEnrolment.getExecutionYear();
        final PersonCustomer personCustomer = PersonCustomer.findUnique(person).get();

        if (!DebtAccount.findUnique(tuitionPaymentPlan.getFinantialEntity().getFinantialInstitution(), personCustomer)
                .isPresent()) {
            DebtAccount.create(tuitionPaymentPlan.getFinantialEntity().getFinantialInstitution(), personCustomer);
        }

        final DebtAccount debtAccount =
                DebtAccount.findUnique(tuitionPaymentPlan.getFinantialEntity().getFinantialInstitution(), personCustomer).get();

        if (!AcademicTreasuryEvent.findUniqueForStandaloneTuition(registration, executionYear).isPresent()) {
            AcademicTreasuryEvent.createForStandaloneTuition(debtAccount, tuitionPaymentPlan.getProduct(), registration,
                    executionYear);
        }

        final AcademicTreasuryEvent academicTreasuryEvent =
                AcademicTreasuryEvent.findUniqueForStandaloneTuition(registration, executionYear).get();

        if (academicTreasuryEvent.getDebtAccount().getFinantialInstitution() != tuitionPaymentPlan.getFinantialEntity()
                .getFinantialInstitution()) {
            throw new AcademicTreasuryDomainException(
                    "error.TuitionServices.standalone.tuition.for.different.finantial.institutions.not.supported");
        }

        
        
        return tuitionPaymentPlan.createDebitEntriesForStandalone(academicTreasuryEvent, standaloneEnrolment, when);
    }

    public static TuitionPaymentPlan usedPaymentPlanForStandalone(final Registration registration,
            final ExecutionYear executionYear, final Enrolment enrolment, final LocalDate debtDate) {
        return usedPaymentPlanForStandalone(registration, executionYear, enrolment, debtDate, null);
    }

    public static TuitionPaymentPlan usedPaymentPlanForStandalone(final Registration registration,
            final ExecutionYear executionYear, final Enrolment enrolment, final LocalDate debtDate,
            final TuitionPaymentPlan tuitionPaymentPlan) {
        if (tuitionPaymentPlan != null) {
            return tuitionPaymentPlan;
        }

        return TuitionPaymentPlan.inferTuitionPaymentPlanForStandaloneEnrolment(registration, executionYear, enrolment);
    }

    @Atomic
    public static List<TuitionDebitEntryBean> calculateInstallmentDebitEntryBeansForStandalone(final Registration registration,
            final ExecutionYear executionYear, final LocalDate debtDate, final Set<Enrolment> enrolments) {
        return calculateInstallmentDebitEntryBeansForStandalone(registration, executionYear, debtDate, null, enrolments);
    }

    @Atomic
    public static List<TuitionDebitEntryBean> calculateInstallmentDebitEntryBeansForStandalone(final Registration registration,
            final ExecutionYear executionYear, final LocalDate debtDate, TuitionPaymentPlan tuitionPaymentPlan,
            final Set<Enrolment> enrolments) {

        final Person person = registration.getPerson();
        // Read person customer
        PersonCustomer personCustomer = PersonCustomer.findUnique(person).orElse(null);

        if (personCustomer == null) {
            personCustomer = PersonCustomer.create(person);
        }

        final List<TuitionDebitEntryBean> entries = Lists.newArrayList();
        for (final Enrolment enrolment : enrolments) {
            if (tuitionPaymentPlan == null) {
                tuitionPaymentPlan =
                        TuitionPaymentPlan.inferTuitionPaymentPlanForStandaloneEnrolment(registration, executionYear, enrolment);
            }

            if (tuitionPaymentPlan == null) {
                continue;
            }

            if (!DebtAccount.findUnique(tuitionPaymentPlan.getFinantialEntity().getFinantialInstitution(), personCustomer)
                    .isPresent()) {
                DebtAccount.create(tuitionPaymentPlan.getFinantialEntity().getFinantialInstitution(), personCustomer);
            }

            final DebtAccount debtAccount =
                    DebtAccount.findUnique(tuitionPaymentPlan.getFinantialEntity().getFinantialInstitution(), personCustomer)
                            .get();

            if (!AcademicTreasuryEvent.findUniqueForStandaloneTuition(registration, executionYear).isPresent()) {
                AcademicTreasuryEvent.createForStandaloneTuition(debtAccount, tuitionPaymentPlan.getProduct(), registration,
                        executionYear);
            }

            final AcademicTreasuryEvent academicTreasuryEvent =
                    AcademicTreasuryEvent.findUniqueForStandaloneTuition(registration, executionYear).get();

            final TuitionInstallmentTariff tuitionInstallmentTariff = tuitionPaymentPlan.getStandaloneTuitionInstallmentTariff();
            final int installmentOrder = tuitionInstallmentTariff.getInstallmentOrder();
            final LocalizedString installmentName = tuitionInstallmentTariff.standaloneDebitEntryName(enrolment);
            final LocalDate dueDate = tuitionInstallmentTariff.dueDate(debtDate);
            final Vat vat = tuitionInstallmentTariff.vat(debtDate);
            final BigDecimal amount = tuitionInstallmentTariff.amountToPay(academicTreasuryEvent, enrolment);

            entries.add(new TuitionDebitEntryBean(installmentOrder, installmentName, dueDate, vat.getTaxRate(), amount));
        }

        return entries.stream().sorted(new Comparator<TuitionDebitEntryBean>() {

            @Override
            public int compare(TuitionDebitEntryBean o1, TuitionDebitEntryBean o2) {
                return o1.getInstallmentOrder() - o2.getInstallmentOrder();
            }

        }).collect(Collectors.toList());
    }

    public static boolean removeDebitEntryForStandaloneEnrolment(final Enrolment standaloneEnrolment) {
        final Registration registration = standaloneEnrolment.getRegistration();
        final ExecutionYear executionYear = standaloneEnrolment.getExecutionYear();

        if (!AcademicTreasuryEvent.findUniqueForStandaloneTuition(registration, executionYear).isPresent()) {
            return false;
        }

        final AcademicTreasuryEvent academicTreasuryEvent =
                AcademicTreasuryEvent.findUniqueForStandaloneTuition(registration, executionYear).get();

        if (!academicTreasuryEvent.isChargedWithDebitEntry(standaloneEnrolment)) {
            return false;
        }

        final DebitEntry debitEntry = academicTreasuryEvent.findActiveEnrolmentDebitEntry(standaloneEnrolment).get();

        if (!debitEntry.isProcessedInDebitNote() || ((DebitNote) debitEntry.getFinantialDocument()).isPreparing()) {
            debitEntry.setCurricularCourse(null);
            debitEntry.setExecutionSemester(null);

            debitEntry.delete();

            return true;
        } else if (((DebitNote) debitEntry.getFinantialDocument()).isClosed()) {
            DebtAccount debtAccount = debitEntry.getDebtAccount();
            final CreditNote creditNote =
                    CreditNote.create(
                            debtAccount,
                            DocumentNumberSeries.findUniqueDefault(FinantialDocumentType.findForCreditNote(),
                                    debtAccount.getFinantialInstitution()).orElse(null), new DateTime(),
                            ((DebitNote) debitEntry.getFinantialDocument()), null);

            CreditEntry.create(creditNote, debitEntry.getDescription(), debitEntry.getProduct(), debitEntry.getVat(),
                    debitEntry.getAmount(), new DateTime(), debitEntry, BigDecimal.ONE);

            debitEntry.annulOnEvent();

            return true;
        }

        return false;
    }

    /* ***************
     * EXTRACURRICULAR
     * ***************
     */

    public static AcademicTreasuryEvent findAcademicTreasuryEventTuitionForExtracurricular(final Registration registration,
            final ExecutionYear executionYear) {
        return AcademicTreasuryEvent.findUniqueForExtracurricularTuition(registration, executionYear).orElse(null);
    }

    @Atomic
    public static boolean createInferedTuitionForExtracurricular(final Enrolment extracurricularEnrolment, final LocalDate when) {
        return createInferedTuitionForExtracurricular(Sets.newHashSet(extracurricularEnrolment), when);
    }

    @Atomic
    public static boolean createInferedTuitionForExtracurricular(final Set<Enrolment> extracurricularEnrolments,
            final LocalDate when) {


        if(AcademicTreasurySettings.getInstance().getTuitionProductGroup() == null) {
            return false;
        }
        
        if(!TuitionPaymentPlanGroup.findUniqueDefaultGroupForExtracurricular().isPresent()) {
            return false;
        }
        
        boolean created = false;

        // Validate all enrolments are extracurricular

        for (final Enrolment extracurricularEnrolment : extracurricularEnrolments) {
            if (!extracurricularEnrolment.isExtraCurricular()) {
                throw new AcademicTreasuryDomainException("error.TuitionServices.enrolment.is.not.extracurricular");
            }
        }

        for (final Enrolment extracurricularEnrolment : extracurricularEnrolments) {
            final Registration registration = extracurricularEnrolment.getRegistration();

            final Person person = registration.getPerson();
            // Read person customer

            if (!PersonCustomer.findUnique(person).isPresent()) {
                PersonCustomer.create(person);
            }

            final ExecutionYear executionYear = extracurricularEnrolment.getExecutionYear();

            if (TuitionPaymentPlan.inferTuitionPaymentPlanForExtracurricularEnrolment(registration, executionYear,
                    extracurricularEnrolment) == null) {
                continue;
            }

            final TuitionPaymentPlan tuitionPaymentPlan =
                    TuitionPaymentPlan.inferTuitionPaymentPlanForExtracurricularEnrolment(registration, executionYear,
                            extracurricularEnrolment);

            created |= createTuitionForExtracurricular(extracurricularEnrolment, tuitionPaymentPlan, when);
        }

        return created;
    }

    @Atomic
    public static boolean createTuitionForExtracurricular(final Enrolment extracurricularEnrolment,
            final TuitionPaymentPlan tuitionPaymentPlan, final LocalDate when) {
        
        if(AcademicTreasurySettings.getInstance().getTuitionProductGroup() == null) {
            return false;
        }
        
        if(!TuitionPaymentPlanGroup.findUniqueDefaultGroupForExtracurricular().isPresent()) {
            return false;
        }
        
        final Registration registration = extracurricularEnrolment.getRegistration();
        final Person person = registration.getPerson();
        final ExecutionYear executionYear = extracurricularEnrolment.getExecutionYear();
        final PersonCustomer personCustomer = PersonCustomer.findUnique(person).get();

        if (!DebtAccount.findUnique(tuitionPaymentPlan.getFinantialEntity().getFinantialInstitution(), personCustomer)
                .isPresent()) {
            DebtAccount.create(tuitionPaymentPlan.getFinantialEntity().getFinantialInstitution(), personCustomer);
        }

        final DebtAccount debtAccount =
                DebtAccount.findUnique(tuitionPaymentPlan.getFinantialEntity().getFinantialInstitution(), personCustomer).get();

        if (!AcademicTreasuryEvent.findUniqueForExtracurricularTuition(registration, executionYear).isPresent()) {
            AcademicTreasuryEvent.createForExtracurricularTuition(debtAccount, tuitionPaymentPlan.getProduct(), registration,
                    executionYear);
        }

        final AcademicTreasuryEvent academicTreasuryEvent =
                AcademicTreasuryEvent.findUniqueForExtracurricularTuition(registration, executionYear).get();

        if (academicTreasuryEvent.getDebtAccount().getFinantialInstitution() != tuitionPaymentPlan.getFinantialEntity()
                .getFinantialInstitution()) {
            throw new AcademicTreasuryDomainException(
                    "error.TuitionServices.standalone.tuition.for.different.finantial.institutions.not.supported");
        }

        return tuitionPaymentPlan.createDebitEntriesForExtracurricular(academicTreasuryEvent, extracurricularEnrolment, when);
    }

    public static TuitionPaymentPlan usedPaymentPlanForExtracurricular(final Registration registration,
            final ExecutionYear executionYear, final Enrolment enrolment, final LocalDate debtDate) {
        return usedPaymentPlanForExtracurricular(registration, executionYear, enrolment, debtDate, null);
    }

    public static TuitionPaymentPlan usedPaymentPlanForExtracurricular(final Registration registration,
            final ExecutionYear executionYear, final Enrolment enrolment, final LocalDate debtDate,
            final TuitionPaymentPlan tuitionPaymentPlan) {
        if (tuitionPaymentPlan != null) {
            return tuitionPaymentPlan;
        }

        return TuitionPaymentPlan.inferTuitionPaymentPlanForExtracurricularEnrolment(registration, executionYear, enrolment);
    }

    @Atomic
    public static List<TuitionDebitEntryBean> calculateInstallmentDebitEntryBeansForExtracurricular(
            final Registration registration, final ExecutionYear executionYear, final LocalDate debtDate,
            final Set<Enrolment> enrolments) {
        return calculateInstallmentDebitEntryBeansForExtracurricular(registration, executionYear, debtDate, null, enrolments);
    }

    @Atomic
    public static List<TuitionDebitEntryBean> calculateInstallmentDebitEntryBeansForExtracurricular(
            final Registration registration, final ExecutionYear executionYear, final LocalDate debtDate,
            TuitionPaymentPlan tuitionPaymentPlan, final Set<Enrolment> enrolments) {

        final Person person = registration.getPerson();
        // Read person customer
        PersonCustomer personCustomer = PersonCustomer.findUnique(person).orElse(null);

        if (personCustomer == null) {
            personCustomer = PersonCustomer.create(person);
        }

        final List<TuitionDebitEntryBean> entries = Lists.newArrayList();
        for (final Enrolment enrolment : enrolments) {
            if (tuitionPaymentPlan == null) {
                tuitionPaymentPlan =
                        TuitionPaymentPlan.inferTuitionPaymentPlanForExtracurricularEnrolment(registration, executionYear,
                                enrolment);
            }

            if (tuitionPaymentPlan == null) {
                continue;
            }

            if (!DebtAccount.findUnique(tuitionPaymentPlan.getFinantialEntity().getFinantialInstitution(), personCustomer)
                    .isPresent()) {
                DebtAccount.create(tuitionPaymentPlan.getFinantialEntity().getFinantialInstitution(), personCustomer);
            }

            final DebtAccount debtAccount =
                    DebtAccount.findUnique(tuitionPaymentPlan.getFinantialEntity().getFinantialInstitution(), personCustomer)
                            .get();

            if (!AcademicTreasuryEvent.findUniqueForExtracurricularTuition(registration, executionYear).isPresent()) {
                AcademicTreasuryEvent.createForExtracurricularTuition(debtAccount, tuitionPaymentPlan.getProduct(), registration,
                        executionYear);
            }

            final AcademicTreasuryEvent academicTreasuryEvent =
                    AcademicTreasuryEvent.findUniqueForExtracurricularTuition(registration, executionYear).get();

            final TuitionInstallmentTariff tuitionInstallmentTariff =
                    tuitionPaymentPlan.getExtracurricularTuitionInstallmentTariff();
            final int installmentOrder = tuitionInstallmentTariff.getInstallmentOrder();
            final LocalizedString installmentName = tuitionInstallmentTariff.extracurricularDebitEntryName(enrolment);
            final LocalDate dueDate = tuitionInstallmentTariff.dueDate(debtDate);
            final Vat vat = tuitionInstallmentTariff.vat(debtDate);
            final BigDecimal amount = tuitionInstallmentTariff.amountToPay(academicTreasuryEvent, enrolment);

            entries.add(new TuitionDebitEntryBean(installmentOrder, installmentName, dueDate, vat.getTaxRate(), amount));
        }

        return entries.stream().sorted(new Comparator<TuitionDebitEntryBean>() {

            @Override
            public int compare(TuitionDebitEntryBean o1, TuitionDebitEntryBean o2) {
                return o1.getInstallmentOrder() - o2.getInstallmentOrder();
            }

        }).collect(Collectors.toList());
    }

    public static boolean removeDebitEntryForExtracurricularEnrolment(final Enrolment extracurricularEnrolment) {
        final Registration registration = extracurricularEnrolment.getRegistration();
        final ExecutionYear executionYear = extracurricularEnrolment.getExecutionYear();

        if (!AcademicTreasuryEvent.findUniqueForExtracurricularTuition(registration, executionYear).isPresent()) {
            return false;
        }

        final AcademicTreasuryEvent academicTreasuryEvent =
                AcademicTreasuryEvent.findUniqueForExtracurricularTuition(registration, executionYear).get();

        if (!academicTreasuryEvent.isChargedWithDebitEntry(extracurricularEnrolment)) {
            return false;
        }

        final DebitEntry debitEntry = academicTreasuryEvent.findActiveEnrolmentDebitEntry(extracurricularEnrolment).get();

        if (!debitEntry.isProcessedInDebitNote() || ((DebitNote) debitEntry.getFinantialDocument()).isPreparing()) {
            debitEntry.setCurricularCourse(null);
            debitEntry.setExecutionSemester(null);

            debitEntry.delete();

            return true;
        } else if (((DebitNote) debitEntry.getFinantialDocument()).isClosed()) {
            DebtAccount debtAccount = debitEntry.getDebtAccount();
            final CreditNote creditNote =
                    CreditNote.create(
                            debtAccount,
                            DocumentNumberSeries.findUniqueDefault(FinantialDocumentType.findForCreditNote(),
                                    debtAccount.getFinantialInstitution()).orElse(null), new DateTime(),
                            ((DebitNote) debitEntry.getFinantialDocument()), null);

            CreditEntry.create(creditNote, debitEntry.getDescription(), debitEntry.getProduct(), debitEntry.getVat(),
                    debitEntry.getAmount(), new DateTime(), debitEntry, BigDecimal.ONE);

            debitEntry.annulOnEvent();

            return true;
        }

        return false;
    }

    /* ----------
     * ENROLMENTS
     * ----------
     */
    
    public static LocalDate enrolmentDate(final Registration registration, final ExecutionYear executionYear, final boolean isToForceCreation) {
        for (final RegistrationDataByExecutionYear registrationDataByExecutionYear : registration.getRegistrationDataByExecutionYearSet()) {
            if(registrationDataByExecutionYear.getExecutionYear() == executionYear && registrationDataByExecutionYear.getEnrolmentDate() != null) {
                return registrationDataByExecutionYear.getEnrolmentDate();
            }
        }
        
        if(isToForceCreation) {
            // Search the enrolment dates for most recent years in which the student was enrolled
            
            int i = 0;
            for(ExecutionYear it = executionYear.getPreviousExecutionYear(); it != null; it = it.getPreviousExecutionYear(), i++) {
                if(registrationDataByExecutionYear(registration, it) != null && registrationDataByExecutionYear(registration, it).getEnrolmentDate() != null) {
                   return registrationDataByExecutionYear(registration, it).getEnrolmentDate().plusYears(i);
                }
            }
        }
        
        return new LocalDate();
    }

    public static List<ExecutionYear> orderedEnrolledExecutionYears(final Registration registration) {
        return registration.getEnrolmentsExecutionYears().stream().sorted(ExecutionYear.REVERSE_COMPARATOR_BY_YEAR)
                .collect(Collectors.toList());
    }

    public static List<ExecutionYear> orderedEnrolledAndImprovementExecutionYears(final Registration registration) {
        Set<ExecutionYear> result = Sets.newHashSet();

        result.addAll(registration.getEnrolmentsExecutionYears().stream().collect(Collectors.toSet()));

        result.addAll(registration.getStudentCurricularPlansSet().stream().map(l -> l.getEnrolmentsSet())
                .reduce((a, b) -> Sets.union(a, b)).orElse(Sets.newHashSet()).stream().map(l -> l.getEvaluationsSet())
                .reduce((a, b) -> Sets.union(a, b)).orElse(Sets.newHashSet()).stream()
                .filter(l -> l.getEvaluationSeason().isImprovement() && l.getExecutionPeriod() != null)
                .map(l -> l.getExecutionPeriod().getExecutionYear()).collect(Collectors.toSet()));

        return result.stream().sorted(ExecutionYear.REVERSE_COMPARATOR_BY_YEAR).collect(Collectors.toList());
    }

    public static Set<Enrolment> normalEnrolments(final Registration registration, final ExecutionYear executionYear) {
        final Set<Enrolment> result = Sets.newHashSet(registration.getEnrolments(executionYear));

        result.removeAll(registration.getStudentCurricularPlan(executionYear).getStandaloneCurriculumLines().stream()
                .filter(l -> l.getExecutionYear() == executionYear && l.isEnrolment()).collect(Collectors.toList()));

        result.removeAll(registration.getStudentCurricularPlan(executionYear).getExtraCurricularCurriculumLines().stream()
                .filter(l -> l.getExecutionYear() == executionYear && l.isEnrolment()).collect(Collectors.toList()));

        return result;
    }

    public static Set<Enrolment> standaloneEnrolments(final Registration registration, final ExecutionYear executionYear) {
        return registration.getStudentCurricularPlan(executionYear).getStandaloneCurriculumLines().stream()
                .filter(l -> l.getExecutionYear() == executionYear && l.isEnrolment()).map(l -> (Enrolment) l)
                .collect(Collectors.<Enrolment> toSet());
    }

    public static Set<Enrolment> extracurricularEnrolments(final Registration registration, final ExecutionYear executionYear) {
        return registration.getStudentCurricularPlan(executionYear).getExtraCurricularCurriculumLines().stream()
                .filter(l -> l.getExecutionYear() == executionYear && l.isEnrolment()).map(l -> (Enrolment) l)
                .collect(Collectors.<Enrolment> toSet());
    }

    public static Set<EnrolmentEvaluation> improvementEnrolments(final Registration registration,
            final ExecutionYear executionYear) {
        final Set<EnrolmentEvaluation> result = Sets.newHashSet();

        for (final Enrolment enrolment : registration.getStudentCurricularPlan(executionYear).getEnroledImprovements()) {
            for (final EnrolmentEvaluation enrolmentEvaluation : enrolment.getEvaluationsSet()) {
                if (!enrolmentEvaluation.getEvaluationSeason().isImprovement()) {
                    continue;
                }

                if (enrolmentEvaluation.getExecutionPeriod() == null) {
                    continue;
                }

                if (enrolmentEvaluation.getExecutionPeriod().getExecutionYear() != executionYear) {
                    continue;
                }

                result.add(enrolmentEvaluation);
            }
        }

        return result;
    }
    
    
    private static RegistrationDataByExecutionYear registrationDataByExecutionYear(final Registration registration, final ExecutionYear executionYear) {
        return registration.getRegistrationDataByExecutionYearSet().stream().filter(l -> l.getExecutionYear() == executionYear).findFirst().orElse(null);
    }

}
