//package org.fenixedu.academictreasury.schoolsbootstrapscript;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//import java.util.Map;
//import java.util.Set;
//import java.util.function.Predicate;
//import java.util.stream.Collectors;
//
//import org.fenixedu.academic.domain.CurricularYear;
//import org.fenixedu.academic.domain.Degree;
//import org.fenixedu.academic.domain.DegreeCurricularPlan;
//import org.fenixedu.academic.domain.EvaluationConfiguration;
//import org.fenixedu.academic.domain.EvaluationSeason;
//import org.fenixedu.academic.domain.ExecutionDegree;
//import org.fenixedu.academic.domain.ExecutionYear;
//import org.fenixedu.academic.domain.administrativeOffice.AdministrativeOffice;
//import org.fenixedu.academic.domain.candidacy.IngressionType;
//import org.fenixedu.academic.domain.degree.DegreeType;
//import org.fenixedu.academic.domain.degree.degreeCurricularPlan.DegreeCurricularPlanState;
//import org.fenixedu.academic.domain.serviceRequests.AcademicServiceRequestSituationType;
//import org.fenixedu.academic.domain.serviceRequests.ServiceRequestCategory;
//import org.fenixedu.academic.domain.serviceRequests.ServiceRequestType;
//import org.fenixedu.academic.domain.serviceRequests.ServiceRequestTypeOption;
//import org.fenixedu.academic.domain.serviceRequests.documentRequests.AcademicServiceRequestType;
//import org.fenixedu.academic.domain.serviceRequests.documentRequests.DocumentRequestType;
//import org.fenixedu.academic.domain.student.RegistrationProtocol;
//import org.fenixedu.academic.domain.student.RegistrationRegimeType;
//import org.fenixedu.academic.domain.student.StatuteType;
//import org.fenixedu.academictreasury.domain.emoluments.AcademicTax;
//import org.fenixedu.academictreasury.domain.emoluments.ServiceRequestMapEntry;
//import org.fenixedu.academictreasury.domain.settings.AcademicTreasurySettings;
//import org.fenixedu.academictreasury.domain.tariff.AcademicTariff;
//import org.fenixedu.academictreasury.domain.tuition.EctsCalculationType;
//import org.fenixedu.academictreasury.domain.tuition.TuitionCalculationType;
//import org.fenixedu.academictreasury.domain.tuition.TuitionPaymentPlan;
//import org.fenixedu.academictreasury.domain.tuition.TuitionPaymentPlanGroup;
//import org.fenixedu.academictreasury.dto.tariff.AcademicTariffBean;
//import org.fenixedu.academictreasury.dto.tariff.TuitionPaymentPlanBean;
//import org.fenixedu.bennu.core.domain.Bennu;
//import org.fenixedu.bennu.core.i18n.BundleUtil;
//import org.fenixedu.bennu.scheduler.custom.CustomTask;
//import org.fenixedu.commons.i18n.LocalizedString;
//import org.fenixedu.treasury.domain.Currency;
//import org.fenixedu.treasury.domain.FinantialEntity;
//import org.fenixedu.treasury.domain.FinantialInstitution;
//import org.fenixedu.treasury.domain.Product;
//import org.fenixedu.treasury.domain.ProductGroup;
//import org.fenixedu.treasury.domain.VatType;
//import org.fenixedu.treasury.domain.exemption.TreasuryExemptionType;
//import org.fenixedu.treasury.domain.settings.TreasurySettings;
//import org.fenixedu.treasury.domain.tariff.DueDateCalculationType;
//import org.fenixedu.treasury.domain.tariff.InterestType;
//import org.joda.time.format.DateTimeFormat;
//
//import com.google.common.collect.Maps;
//import com.google.common.collect.Sets;
//
//public class SchoolsBootstrapCustomTask extends CustomTask {
//
//    // @formatter:off
//
//    /******************************************
//     * ++++ THINGS TO DO FIRST ++++
//     * 
//     * 1. Migrate Finantial Institutions
//     * 2. Open and make current 2015/2016
//     * 3. Create Execution Degrees
//     * ****************************************
//     */
//
//    private static final String PAGAMENTO_EM_AVANCO = "PAGAMENTO_EM_AVANCO";
//    private static final String INTEREST_CODE = "JURO";
//    private static final Map<String, String> FI_LOOKUP = Maps.newHashMap();
//    private static final int NOT_APPLIED = -1;
//
//    static {
////        FI_LOOKUP.put("FMD", "503013366");
////        FI_LOOKUP.put("FL", "999999992");
//        FI_LOOKUP.put("FF", "502659807");
////        FI_LOOKUP.put("RUL", "999999994");
////        FI_LOOKUP.put("FMV", "999999995");
//    }
//
//    @Override
//    public void runTask() throws Exception {
//
////        getLogger().info("createMissingEvaluationSeasons()");
////        createMissingEvaluationSeasons();
////        
////        getLogger().info("createMissingIngressions()");
////        createMissingIngressions();
////
////        getLogger().info("createDefaultServiceRequestTypes()");
////        createDefaultServiceRequestTypes();
////
////        getLogger().info("defineMappingFinantialEntityAdministrativeOffice()");
////        defineMappingFinantialEntityAdministrativeOffice();
////
////        getLogger().info("createDefaultProductGroups()");
////        createDefaultProductGroups();
////
////        getLogger().info("createProductForInterest()");
////        createProductForInterest();
////
////        getLogger().info("createProductForAdvancePayment()");
////        createProductForAdvancePayment();
////
////        getLogger().info("configureTreasurySettings()");
////        configureTreasurySettings();
////
////        getLogger().info("createProducts_FROM_SPREADSHEET()");
////        createProducts_FROM_SPREADSHEET();
//
//        getLogger().info("createAcademicTaxes_FROM_SPREADSHEET()");
//        createAcademicTaxes_FROM_SPREADSHEET();
//
//        getLogger().info("createTuitionPaymentPlanGroups_FROM_SPREADSHEET()");
//        createTuitionPaymentPlanGroups_FROM_SPREADSHEET();
//
//        getLogger().info("configureAcademicTreasurySettings_FROM_SPREADSHEET()");
//        configureAcademicTreasurySettings_FROM_SPREADSHEET();
//
//        getLogger().info("createServiceRequestTypesToProducts_FROM_SPREADSHEET()");
//        createServiceRequestTypesToProducts_FROM_SPREADSHEET();
//
//        getLogger().info("createExemptionTypes_FROM_SPREADSHEET()");
//        createExemptionTypes_FROM_SPREADSHEET();
//
//        getLogger().info("createEmolumentTariffs_FROM_SPREADSHEET()");
//        createEmolumentTariffs_FROM_SPREADSHEET();
//
//        getLogger().info("createTuitionForRegistrationTariffs_FROM_SPREADSHEET()");
//        createTuitionForRegistrationTariffs_FROM_SPREADSHEET();
//
//        getLogger().info("createStandaloneTariffs_FROM_SPREADSHEET()");
//        createStandaloneTariffs_FROM_SPREADSHEET();
//
//        getLogger().info("createExtracurricularTariffs()");
//        createExtracurricularTariffs();
//    }
//
//    private void createExtracurricularTariffs() {
//
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForExtracurricular().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForExtracurricular().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean
//                    .setDegreeCurricularPlans(readDegreeCurricularPlans("BOLONHA_INTEGRATED_MASTER_DEGREE", "", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setWithLaboratorialClasses(false);
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate(""));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(TuitionPaymentPlanGroup
//                    .findUniqueDefaultGroupForExtracurricular().get().getCurrentProduct());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.ECTS);
//            tuitionPaymentPlanBean.setFixedAmount(amount("15"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType("FIXED_AMOUNT"));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOn(true);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForExtracurricular().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForExtracurricular().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("BOLONHA_MASTER_DEGREE", "", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setWithLaboratorialClasses(false);
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate(""));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(TuitionPaymentPlanGroup
//                    .findUniqueDefaultGroupForExtracurricular().get().getCurrentProduct());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.ECTS);
//            tuitionPaymentPlanBean.setFixedAmount(amount("15"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType("FIXED_AMOUNT"));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOn(true);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//    }
//
//    private void createMissingEvaluationSeasons() {
//
//        if (EvaluationSeason.readNormalSeason() == null) {
//            EvaluationSeason season =
//                    new EvaluationSeason(new LocalizedString(pt(), "NORMAL"), new LocalizedString(pt(), "Normal"), true, false,
//                            false, false);
//            season.setCode("NORMAL");
//        }
//
//        if (EvaluationSeason.readImprovementSeason() == null) {
//            EvaluationSeason season =
//                    new EvaluationSeason(new LocalizedString(pt(), "MELHORIA"), new LocalizedString(pt(), "Melhoria"), false,
//                            true, false, false);
//            season.setCode("MELHORIA");
//        }
//
//        if (EvaluationSeason.readSpecialSeason() == null) {
//            EvaluationSeason season =
//                    new EvaluationSeason(new LocalizedString(pt(), "EPOCA_ESPECIAL"),
//                            new LocalizedString(pt(), "Época Especial"), false, false, false, true);
//            season.setCode("EPOCA_ESPECIAL");
//        }
//
//        EvaluationConfiguration.getInstance().setDefaultEvaluationSeason(EvaluationSeason.readNormalSeason());
//    }
//
//    private void createStandaloneTariffs_FROM_SPREADSHEET() {
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean
//                    .setDegreeCurricularPlans(readDegreeCurricularPlans("BOLONHA_INTEGRATED_MASTER_DEGREE", "", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression("ALUNOS_ULISBOA"));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setWithLaboratorialClasses(true);
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate(""));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone()
//                    .get().getCurrentProduct());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.ECTS);
//            tuitionPaymentPlanBean.setFixedAmount(amount("50"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType("FIXED_AMOUNT"));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOn(true);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean
//                    .setDegreeCurricularPlans(readDegreeCurricularPlans("BOLONHA_INTEGRATED_MASTER_DEGREE", "", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression("ALUNOS_ULISBOA"));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setWithLaboratorialClasses(false);
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate(""));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone()
//                    .get().getCurrentProduct());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.ECTS);
//            tuitionPaymentPlanBean.setFixedAmount(amount("30"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType("FIXED_AMOUNT"));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOn(true);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("BOLONHA_MASTER_DEGREE", "", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression("ALUNOS_ULISBOA"));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setWithLaboratorialClasses(true);
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate(""));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone()
//                    .get().getCurrentProduct());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.ECTS);
//            tuitionPaymentPlanBean.setFixedAmount(amount("100"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType("FIXED_AMOUNT"));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOn(true);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("BOLONHA_MASTER_DEGREE", "", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression("ALUNOS_ULISBOA"));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setWithLaboratorialClasses(false);
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate(""));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone()
//                    .get().getCurrentProduct());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.ECTS);
//            tuitionPaymentPlanBean.setFixedAmount(amount("40"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType("FIXED_AMOUNT"));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOn(true);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("BOLONHA_PHD", "", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression("ALUNOS_ULISBOA"));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setWithLaboratorialClasses(true);
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate(""));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone()
//                    .get().getCurrentProduct());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.ECTS);
//            tuitionPaymentPlanBean.setFixedAmount(amount("150"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType("FIXED_AMOUNT"));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOn(true);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("BOLONHA_PHD", "", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression("ALUNOS_ULISBOA"));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setWithLaboratorialClasses(false);
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate(""));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone()
//                    .get().getCurrentProduct());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.ECTS);
//            tuitionPaymentPlanBean.setFixedAmount(amount("60"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType("FIXED_AMOUNT"));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOn(true);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean
//                    .setDegreeCurricularPlans(readDegreeCurricularPlans("BOLONHA_INTEGRATED_MASTER_DEGREE", "", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression("ALUNOS_EXTERNOS_ULISBOA"));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setWithLaboratorialClasses(true);
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate(""));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone()
//                    .get().getCurrentProduct());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.ECTS);
//            tuitionPaymentPlanBean.setFixedAmount(amount("62.5"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType("FIXED_AMOUNT"));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOn(true);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean
//                    .setDegreeCurricularPlans(readDegreeCurricularPlans("BOLONHA_INTEGRATED_MASTER_DEGREE", "", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression("ALUNOS_EXTERNOS_ULISBOA"));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setWithLaboratorialClasses(false);
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate(""));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone()
//                    .get().getCurrentProduct());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.ECTS);
//            tuitionPaymentPlanBean.setFixedAmount(amount("37.5"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType("FIXED_AMOUNT"));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOn(true);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("BOLONHA_MASTER_DEGREE", "", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression("ALUNOS_EXTERNOS_ULISBOA"));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setWithLaboratorialClasses(true);
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate(""));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone()
//                    .get().getCurrentProduct());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.ECTS);
//            tuitionPaymentPlanBean.setFixedAmount(amount("125"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType("FIXED_AMOUNT"));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOn(true);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("BOLONHA_MASTER_DEGREE", "", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression("ALUNOS_EXTERNOS_ULISBOA"));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setWithLaboratorialClasses(false);
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate(""));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone()
//                    .get().getCurrentProduct());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.ECTS);
//            tuitionPaymentPlanBean.setFixedAmount(amount("50"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType("FIXED_AMOUNT"));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOn(true);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("BOLONHA_PHD", "", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression("ALUNOS_EXTERNOS_ULISBOA"));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setWithLaboratorialClasses(true);
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate(""));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone()
//                    .get().getCurrentProduct());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.ECTS);
//            tuitionPaymentPlanBean.setFixedAmount(amount("187.5"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType("FIXED_AMOUNT"));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOn(true);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("BOLONHA_PHD", "", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression("ALUNOS_EXTERNOS_ULISBOA"));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setWithLaboratorialClasses(false);
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate(""));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(TuitionPaymentPlanGroup.findUniqueDefaultGroupForStandalone()
//                    .get().getCurrentProduct());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.ECTS);
//            tuitionPaymentPlanBean.setFixedAmount(amount("75"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType("FIXED_AMOUNT"));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//    }
//
//    private void createMissingIngressions() {
//
//        if (!IngressionType.findIngressionTypeByCode("ESTUDANTE_INTERNACIONAL").isPresent()) {
//            IngressionType.createIngressionType("ESTUDANTE_INTERNACIONAL", new LocalizedString(pt(), "Estudante Internacional"));
//        }
//
//        if (!IngressionType.findIngressionTypeByCode("ALUNOS_ULISBOA").isPresent()) {
//            IngressionType.createIngressionType("ALUNOS_ULISBOA", new LocalizedString(pt(), "Alunos da Universidade de Lisboa"));
//        }
//
//        if (!IngressionType.findIngressionTypeByCode("ALUNOS_EXTERNOS_ULISBOA").isPresent()) {
//            IngressionType.createIngressionType("ALUNOS_EXTERNOS_ULISBOA", new LocalizedString(pt(),
//                    "Alunos Externos a Universidade de Lisboa"));
//        }
//    }
//
//    private void createTuitionForRegistrationTariffs_FROM_SPREADSHEET() {
//
//        //FF    PARCIAL BOLONHA_INTEGRATED_MASTER_DEGREE
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean
//                    .setDegreeCurricularPlans(readDegreeCurricularPlans("BOLONHA_INTEGRATED_MASTER_DEGREE", "", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType("PARTIAL_TIME"));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setFirstTimeStudent(false);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/09/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("334.05"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/10/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("44.65"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/11/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_3_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("44.65"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/01/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_4_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("44.65"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/02/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_5_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("44.65"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/03/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_6_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("44.65"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/04/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_7_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("44.65"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/05/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_8_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("44.65"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/06/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_9_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("44.65"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        //FF    1º ANO 1º VEZ   BOLONHA_INTEGRATED_MASTER_DEGREE
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean
//                    .setDegreeCurricularPlans(readDegreeCurricularPlans("BOLONHA_INTEGRATED_MASTER_DEGREE", "", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType(""));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setFirstTimeStudent(true);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/09/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("551.47"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/10/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("64"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/11/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_3_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("64"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/01/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_4_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("64"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/02/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_5_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("64"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/03/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_6_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("64"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/04/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_7_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("64"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/05/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_8_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("64"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/06/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_9_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("64"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        //FF    PRINCIPAL   BOLONHA_INTEGRATED_MASTER_DEGREE
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean
//                    .setDegreeCurricularPlans(readDegreeCurricularPlans("BOLONHA_INTEGRATED_MASTER_DEGREE", "", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(true);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType(""));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setFirstTimeStudent(false);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/09/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("367.47"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/10/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("87"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/11/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_3_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("87"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/01/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_4_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("87"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/02/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_5_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("87"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/03/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_6_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("87"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/04/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_7_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("87"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/05/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_8_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("87"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("26/06/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_9_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("87"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   1º ANO 1º VEZ, PARCIAL  BOLONHA_MASTER_DEGREE   138                                                                                     
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("BOLONHA_MASTER_DEGREE", "",
//                    "M. Analises Clinicas, Reg Aval Med e Prod de Saude, M. Cuidados Farmaceuticos, M. Farmacot. Farmacoepi."));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType("PARTIAL_TIME"));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setFirstTimeStudent(true);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/10/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("300"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/01/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("168.75"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/03/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_3_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("168.75"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/05/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_4_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("168.75"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/06/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_5_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("168.75"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   PARCIAL BOLONHA_MASTER_DEGREE
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("BOLONHA_MASTER_DEGREE", "",
//                    "M. Analises Clinicas, Reg Aval Med e Prod de Saude, M. Cuidados Farmaceuticos, M. Farmacot. Farmacoepi."));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType("PARTIAL_TIME"));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setFirstTimeStudent(false);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/10/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("227.5"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/01/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("227.5"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/03/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_3_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("227.5"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/05/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_4_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("227.5"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/06/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_5_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("227.5"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   1º ANO 1º VEZ   BOLONHA_MASTER_DEGREE
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("BOLONHA_MASTER_DEGREE", "",
//                    "M. Analises Clinicas, Reg Aval Med e Prod de Saude, M. Cuidados Farmaceuticos, M. Farmacot. Farmacoepi."));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType(""));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setFirstTimeStudent(true);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/10/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("500"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/01/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("250"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/03/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_3_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("250"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/05/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_4_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("250"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/06/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_5_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("250"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   PRINCIPAL   BOLONHA_MASTER_DEGREE
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("BOLONHA_MASTER_DEGREE", "",
//                    "M. Analises Clinicas, Reg Aval Med e Prod de Saude, M. Cuidados Farmaceuticos, M. Farmacot. Farmacoepi."));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(true);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType(""));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setFirstTimeStudent(false);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/10/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("350"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/01/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("350"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/03/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_3_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("350"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/05/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_4_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("350"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/06/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_5_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("350"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   CUSTOMIZADO [Prorrogação Dissertação]   BOLONHA_MASTER_DEGREE
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("BOLONHA_MASTER_DEGREE", "",
//                    "M. Analises Clinicas, Reg Aval Med e Prod de Saude, M. Cuidados Farmaceuticos, M. Farmacot. Farmacoepi."));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType(""));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(true);
//            tuitionPaymentPlanBean.setName("Prorrogação Dissertação");
//            tuitionPaymentPlanBean.setFirstTimeStudent(false);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/01/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("145.83"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/02/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("145.83"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/03/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_3_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("145.83"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/04/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_4_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("145.83"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/05/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_5_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("145.83"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/06/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_6_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("145.83"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/07/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_6_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("437.5"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/09/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_7_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("437.52"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   CUSTOMIZADO [Aluno 1º ano repetente]    BOLONHA_MASTER_DEGREE
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("BOLONHA_MASTER_DEGREE", "",
//                    "M. Analises Clinicas, Reg Aval Med e Prod de Saude, M. Cuidados Farmaceuticos, M. Farmacot. Farmacoepi."));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType(""));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(true);
//            tuitionPaymentPlanBean.setName("Aluno 1º ano repetente");
//            tuitionPaymentPlanBean.setFirstTimeStudent(false);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/10/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.ECTS);
//            tuitionPaymentPlanBean.setFixedAmount(amount("25"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType("FIXED_AMOUNT"));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   1º ANO 1º VEZ, PARCIAL  M. Analises Clinicas
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("", "M. Analises Clinicas", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType("PARTIAL_TIME"));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setFirstTimeStudent(true);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/10/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("600.5"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/01/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("459.25"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/03/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_3_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("459.25"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/05/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_4_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("459.25"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/06/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_5_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("459.25"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   PARCIAL M. Analises Clinicas
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("", "M. Analises Clinicas", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType("PARTIAL_TIME"));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setFirstTimeStudent(false);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/10/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("195"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/01/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("195"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/04/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_3_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("195"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/07/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_4_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("195"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/09/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_5_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("195"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   1º ANO 1º VEZ   M. Analises Clinicas
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("", "M. Analises Clinicas", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType(""));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setFirstTimeStudent(true);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/10/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("1000"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/01/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("687.5"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/03/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_3_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("687.5"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/05/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_4_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("687.5"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/06/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_5_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("687.5"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   PRINCIPAL   M. Analises Clinicas
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("", "M. Analises Clinicas", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(true);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType(""));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setFirstTimeStudent(false);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/10/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("300"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/01/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("300"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/04/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_3_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("300"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/07/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_4_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("300"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/09/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_5_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("300"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   CUSTOMIZADO [Prorrogação Dissertação]   M. Analises Clinicas
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("", "M. Analises Clinicas", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType(""));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(true);
//            tuitionPaymentPlanBean.setName("Prorrogação Dissertação");
//            tuitionPaymentPlanBean.setFirstTimeStudent(false);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/01/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("125"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/02/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("125"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/03/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_3_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("125"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/04/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_4_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("125"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/05/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_5_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("125"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/06/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_6_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("125"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/07/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_7_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("375"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/09/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_8_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("375"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   CUSTOMIZADO [Aluno 1º ano repetente]    M. Analises Clinicas
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("", "M. Analises Clinicas", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType(""));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(true);
//            tuitionPaymentPlanBean.setName("Aluno 1º ano repetente");
//            tuitionPaymentPlanBean.setFirstTimeStudent(false);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/10/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.ECTS);
//            tuitionPaymentPlanBean.setFixedAmount(amount("62.5"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType("FIXED_AMOUNT"));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   1º ANO 1º VEZ, PARCIAL  Reg Aval Med e Prod de Saude
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("", "Reg Aval Med e Prod de Saude", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType("PARTIAL_TIME"));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setFirstTimeStudent(true);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/10/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("585"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/01/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("585"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/03/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_3_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("585"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/05/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_4_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("585"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/06/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_5_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("585"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   PARCIAL Reg Aval Med e Prod de Saude
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("", "Reg Aval Med e Prod de Saude", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType("PARTIAL_TIME"));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setFirstTimeStudent(false);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/10/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("325"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/01/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("325"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   1º ANO 1º VEZ   Reg Aval Med e Prod de Saude
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("", "Reg Aval Med e Prod de Saude", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType(""));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setFirstTimeStudent(true);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/10/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("900"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/01/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("900"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/03/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_3_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("900"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/05/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_4_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("900"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/06/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_5_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("900"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   PRINCIPAL   Reg Aval Med e Prod de Saude
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("", "Reg Aval Med e Prod de Saude", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(true);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType(""));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setFirstTimeStudent(false);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/10/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("500"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/04/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("500"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   CUSTOMIZADO [Prorrogação Dissertação]   Reg Aval Med e Prod de Saude
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("", "Reg Aval Med e Prod de Saude", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType(""));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(true);
//            tuitionPaymentPlanBean.setName("Prorrogação Dissertação");
//            tuitionPaymentPlanBean.setFirstTimeStudent(false);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/01/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("83.33"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/02/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("83.33"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/03/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_3_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("83.33"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/04/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_4_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("83.33"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/05/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_5_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("83.33"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/06/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_6_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("83.33"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/07/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_7_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("250"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/09/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_8_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("250.02"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   CUSTOMIZADO [Aluno 1º ano repetente]    Reg Aval Med e Prod de Saude
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("", "Reg Aval Med e Prod de Saude", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType(""));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(true);
//            tuitionPaymentPlanBean.setName("Aluno 1º ano repetente");
//            tuitionPaymentPlanBean.setFirstTimeStudent(false);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/10/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.ECTS);
//            tuitionPaymentPlanBean.setFixedAmount(amount("75"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType("FIXED_AMOUNT"));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   PARCIAL M. Cuidados Farmaceuticos
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("", "M. Cuidados Farmaceuticos", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType(""));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setFirstTimeStudent(false);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/10/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("325"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/01/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("325"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   PRINCIPAL   M. Cuidados Farmaceuticos
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("", "M. Cuidados Farmaceuticos", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(true);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType(""));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setFirstTimeStudent(false);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/10/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("500"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/01/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("500"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   CUSTOMIZADO [Prorrogação Dissertação]   M. Cuidados Farmaceuticos
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("", "M. Cuidados Farmaceuticos", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType(""));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(true);
//            tuitionPaymentPlanBean.setName("Prorrogação Dissertação");
//            tuitionPaymentPlanBean.setFirstTimeStudent(false);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/01/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("83.33"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/02/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("83.33"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/03/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_3_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("83.33"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/04/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_4_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("83.33"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/05/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_5_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("83.33"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/06/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_6_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("83.33"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/07/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_7_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("250"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/09/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_8_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("250.02"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   CUSTOMIZADO [Aluno 1º ano repetente]    M. Cuidados Farmaceuticos
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("", "M. Cuidados Farmaceuticos", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType(""));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(true);
//            tuitionPaymentPlanBean.setName("Aluno 1º ano repetente");
//            tuitionPaymentPlanBean.setFirstTimeStudent(false);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/10/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.ECTS);
//            tuitionPaymentPlanBean.setFixedAmount(amount("16.67"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType("FIXED_AMOUNT"));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   PARCIAL M. Farmacot. Farmacoepi.
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("", "M. Farmacot. Farmacoepi.", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType(""));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setFirstTimeStudent(false);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/10/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("325"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/01/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("325"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   PRINCIPAL   M. Farmacot. Farmacoepi.
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("", "M. Farmacot. Farmacoepi.", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(true);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType(""));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setFirstTimeStudent(false);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/10/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("500"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/01/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("500"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   CUSTOMIZADO [Prorrogação Dissertação]   M. Farmacot. Farmacoepi.
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("", "M. Farmacot. Farmacoepi.", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType(""));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(true);
//            tuitionPaymentPlanBean.setName("Prorrogação Dissertação");
//            tuitionPaymentPlanBean.setFirstTimeStudent(false);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/01/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("83.33"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/02/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("83.33"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/03/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_3_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("83.33"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/04/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_4_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("83.33"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/05/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_5_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("83.33"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/06/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_6_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("83.33"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/07/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_7_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("250"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/09/2016"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_8_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("250.02"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        //  FF  CUSTOMIZADO [Aluno 1º ano repetente]    M. Farmacot. Farmacoepi.
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("", "M. Farmacot. Farmacoepi.", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType(""));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(true);
//            tuitionPaymentPlanBean.setName("Aluno 1º ano repetente");
//            tuitionPaymentPlanBean.setFirstTimeStudent(false);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.BEST_OF_FIXED_DATE_AND_DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate("20/10/2015"));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(true);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.ECTS);
//            tuitionPaymentPlanBean.setFixedAmount(amount("16.67"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType("FIXED_AMOUNT"));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   PARCIAL BOLONHA_PHD
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("BOLONHA_PHD", "", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType("PARTIAL_TIME"));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setFirstTimeStudent(false);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate(""));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(false);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("446.89"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate(""));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(90);
//            tuitionPaymentPlanBean.setApplyInterests(false);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("446.87"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate(""));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(180);
//            tuitionPaymentPlanBean.setApplyInterests(false);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_3_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("446.87"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate(""));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(270);
//            tuitionPaymentPlanBean.setApplyInterests(false);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_4_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("446.87"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   PRINCIPAL   BOLONHA_PHD
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("BOLONHA_PHD", "", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(true);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType(""));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(false);
//            tuitionPaymentPlanBean.setName("");
//            tuitionPaymentPlanBean.setFirstTimeStudent(false);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate(""));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(180);
//            tuitionPaymentPlanBean.setApplyInterests(false);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("687.5"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate(""));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(270);
//            tuitionPaymentPlanBean.setApplyInterests(false);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("687.5"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate(""));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(false);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("446.89"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate(""));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(90);
//            tuitionPaymentPlanBean.setApplyInterests(false);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("446.87"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//        // FF   CUSTOMIZADO [Prorrogação Tese]  BOLONHA_PHD
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            TuitionPaymentPlanBean tuitionPaymentPlanBean =
//                    new TuitionPaymentPlanBean(TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get()
//                            .getCurrentProduct(), TuitionPaymentPlanGroup.findUniqueDefaultGroupForRegistration().get(),
//                            oneOfFinantialEntity("FF"), defaultExecutionYear());
//            tuitionPaymentPlanBean.setDegreeCurricularPlans(readDegreeCurricularPlans("BOLONHA_PHD", "", ""));
//            tuitionPaymentPlanBean.setDefaultPaymentPlan(false);
//            tuitionPaymentPlanBean.setRegistrationRegimeType(registrationRegimeType(""));
//            tuitionPaymentPlanBean.setRegistrationProtocol(registrationProtocol(""));
//            tuitionPaymentPlanBean.setIngression(ingression(""));
//            tuitionPaymentPlanBean.setCurricularYear(curricularYear(""));
//            tuitionPaymentPlanBean.setStatuteType(statuteType(""));
//            tuitionPaymentPlanBean.setCustomized(true);
//            tuitionPaymentPlanBean.setName("Prorrogação Tese");
//            tuitionPaymentPlanBean.setFirstTimeStudent(false);
//
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate(""));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(0);
//            tuitionPaymentPlanBean.setApplyInterests(false);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_1_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("375"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate(""));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(90);
//            tuitionPaymentPlanBean.setApplyInterests(false);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_2_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("375"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate(""));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(180);
//            tuitionPaymentPlanBean.setApplyInterests(false);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_3_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("375"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            tuitionPaymentPlanBean.setBeginDate(defaultExecutionYear().getBeginLocalDate());
//            tuitionPaymentPlanBean.setEndDate(null);
//            tuitionPaymentPlanBean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            tuitionPaymentPlanBean.setFixedDueDate(fixedDueDate(""));
//            tuitionPaymentPlanBean.setNumberOfDaysAfterCreationForDueDate(270);
//            tuitionPaymentPlanBean.setApplyInterests(false);
//            if (tuitionPaymentPlanBean.isApplyInterests()) {
//                tuitionPaymentPlanBean.setInterestType(InterestType.GLOBAL_RATE);
//            }
//            tuitionPaymentPlanBean.setApplyInFirstWorkday(true);
//            tuitionPaymentPlanBean.setTuitionInstallmentProduct(Product.findUniqueByCode("PROPINA_MATRICULA_4_PRESTACAO").get());
//            tuitionPaymentPlanBean.setTuitionCalculationType(TuitionCalculationType.FIXED_AMOUNT);
//            tuitionPaymentPlanBean.setFixedAmount(amount("375"));
//            tuitionPaymentPlanBean.setEctsCalculationType(ectsCalculationType(""));
//            tuitionPaymentPlanBean.setFactor(amount(""));
//            tuitionPaymentPlanBean.setTotalEctsOrUnits(amount(""));
//            tuitionPaymentPlanBean.setAcademicalActBlockingOff(false);
//            tuitionPaymentPlanBean.addInstallment();
//            TuitionPaymentPlan.create(tuitionPaymentPlanBean);
//        }
//
//    }
//
//    private EctsCalculationType ectsCalculationType(String value) {
//        if (value.trim().isEmpty()) {
//            return null;
//        }
//
//        return EctsCalculationType.valueOf(value);
//    }
//
//    private StatuteType statuteType(final String value) {
//        if (value.trim().isEmpty()) {
//            return null;
//        }
//
//        for (final StatuteType statuteType : Bennu.getInstance().getStatuteTypesSet()) {
//            if (statuteType.getCode().equals(value.trim())) {
//                return statuteType;
//            }
//        }
//
//        return null;
//    }
//
//    private CurricularYear curricularYear(String value) {
//        if (value.trim().isEmpty()) {
//            return null;
//        }
//
//        return CurricularYear.readByYear(Integer.valueOf(value));
//    }
//
//    private IngressionType ingression(String value) {
//        if (value.trim().isEmpty()) {
//            return null;
//        }
//
//        return IngressionType.findIngressionTypeByCode(value.trim()).get();
//    }
//
//    private RegistrationProtocol registrationProtocol(final String value) {
//        if (value.trim().isEmpty()) {
//            return null;
//        }
//
//        for (final RegistrationProtocol registrationProtocol : Bennu.getInstance().getRegistrationProtocolsSet()) {
//            if (registrationProtocol.getCode().equals(value.trim())) {
//                return registrationProtocol;
//            }
//        }
//
//        return null;
//    }
//
//    private RegistrationRegimeType registrationRegimeType(String value) {
//        if (value.trim().isEmpty()) {
//            return null;
//        }
//
//        return RegistrationRegimeType.valueOf(value);
//    }
//
//    private Set<DegreeCurricularPlan> readDegreeCurricularPlans(final String degreeTypeValues, final String degreeCode,
//            final String degreesToExcludeCode) {
//        if (!degreeCode.trim().isEmpty()) {
//            final Set<DegreeCurricularPlan> result = Degree.readBySigla(degreeCode).getDegreeCurricularPlansSet();
//
//            return result.stream()
//                    .filter(t -> ExecutionDegree.getByDegreeCurricularPlanAndExecutionYear(t, defaultExecutionYear()) != null)
//                    .collect(Collectors.toSet());
//        }
//
//        final Set<DegreeCurricularPlan> result =
//                Sets.newHashSet(DegreeCurricularPlan.readByDegreeTypeAndState(new Predicate<DegreeType>() {
//
//                    @Override
//                    public boolean test(DegreeType t) {
//                        return degreeTypes(degreeTypeValues).contains(t);
//                    }
//                }, DegreeCurricularPlanState.ACTIVE));
//
//        if (!degreesToExcludeCode.trim().isEmpty()) {
//            for (String dcpToExclude : degreesToExcludeCode.split(",")) {
//                result.removeAll(Degree.readBySigla(dcpToExclude.trim()).getDegreeCurricularPlansSet());
//            }
//        }
//
//        return result.stream()
//                .filter(t -> ExecutionDegree.getByDegreeCurricularPlanAndExecutionYear(t, defaultExecutionYear()) != null)
//                .collect(Collectors.toSet());
//    }
//
//    private Set<DegreeType> degreeTypes(final String degreeTypeValues) {
//        final Set<DegreeType> result = Sets.newHashSet();
//        for (final String degreeType : degreeTypeValues.split(",")) {
//            if (degreeType.trim().isEmpty()) {
//                continue;
//            }
//
//            result.add(findDegreeTypeByCode(degreeType.trim()));
//        }
//
//        return result;
//    }
//
//    private ExecutionYear defaultExecutionYear() {
//        return ExecutionYear.readCurrentExecutionYear();
//    }
//
//    private void defineMappingFinantialEntityAdministrativeOffice() {
//        FinantialInstitution.findUniqueByFiscalCode(FI_LOOKUP.entrySet().iterator().next().getValue()).get()
//                .getFinantialEntitiesSet().iterator().next()
//                .setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//    }
//
//    private void createExemptionTypes_FROM_SPREADSHEET() {
//
//        if (!fromAcronymsToFinantialInstitutionList("FMD, FF").isEmpty()) {
//            TreasuryExemptionType
//                    .create("ISENCAO_DOUTORAMENTO_DOCENTE",
//                            new LocalizedString(pt(),
//                                    "Isenção em Propina de Doutoramento para docentes abrangidos pelo Artigo  4.º, n.º 4, do Decreto--Lei n.º 216/92")
//                                    .with(en(), ""), new BigDecimal(100), true);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FMD, FF").isEmpty()) {
//            TreasuryExemptionType
//                    .create("ISENCAO_DOUTORAMENTO_NAO_DOCENTE",
//                            new LocalizedString(pt(),
//                                    "Docentes da FMDUL não abrangidos pelo Artigo 4.º, n.º 4, do Decreto -Lei n.º 216/92 e Funcionários não docentes da FMDUL")
//                                    .with(en(), ""), new BigDecimal(70), true);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FMD, FF").isEmpty()) {
//            TreasuryExemptionType.create("ISENCAO_IRS_ADSE_SS_MIL_PASSES_SOCIAIS", new LocalizedString(pt(),
//                    "IRS, ADSE, Segurança Social, prestações familiares, militares, passes sociais e bolsas").with(en(), ""),
//                    new BigDecimal(100), true);
//        }
//    }
//
//    private void createDefaultProductGroups() {
//        if (ProductGroup.findByCode("TUITION") == null) {
//            ProductGroup.create("TUITION", new LocalizedString(pt(), "Propina").with(en(), "Tuition"));
//        }
//
//        if (ProductGroup.findByCode("EMOLUMENT") == null) {
//            ProductGroup.create("EMOLUMENT", new LocalizedString(pt(), "Emolumento").with(en(), "Emolument"));
//        }
//
//        if (ProductGroup.findByCode("OTHER") == null) {
//            ProductGroup.create("OTHER", new LocalizedString(pt(), "Outro").with(en(), "Other"));
//        }
//    }
//
//    private void configureTreasurySettings() {
//        TreasurySettings.getInstance().edit(Currency.findByCode("EUR"), Product.findUniqueByCode(INTEREST_CODE).get(),
//                Product.findUniqueByCode(PAGAMENTO_EM_AVANCO).get());
//    }
//
//    private void createProductForInterest() {
//        if (Product.findUniqueByCode(INTEREST_CODE).isPresent()) {
//            return;
//        }
//
//        final ProductGroup productGroup = ProductGroup.findByCode("OTHER");
//        LocalizedString productName = new LocalizedString(pt(), "Juro").with(en(), "Interest");
//        Product.create(productGroup, INTEREST_CODE, productName, defaultUnitOfMeasure(), true, VatType.findByCode("ISE"),
//                FinantialInstitution.findAll().collect(Collectors.toList()));
//
//    }
//
//    private void createProductForAdvancePayment() {
//        if (Product.findUniqueByCode(PAGAMENTO_EM_AVANCO).isPresent()) {
//            return;
//        }
//
//        final ProductGroup productGroup = ProductGroup.findByCode("OTHER");
//        LocalizedString productName = new LocalizedString(pt(), "Pagamento em avanço").with(en(), "Advanced payment");
//        Product.create(productGroup, PAGAMENTO_EM_AVANCO, productName, defaultUnitOfMeasure(), true, VatType.findByCode("ISE"),
//                FinantialInstitution.findAll().collect(Collectors.toList()));
//    }
//
//    private void createEmolumentTariffs_FROM_SPREADSHEET() {
//
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("TAXA_INSCRICAO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(25));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(0));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("TAXA_CANDIDATURA").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(10));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(0));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CANDIDATURA_MAIORES_23_ANOS").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(60));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(0));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CANDIDATURA_OUTRO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(60));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(0));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CANDIDATURA_MESTRADO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(75));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(0));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CANDIDATURA_DOUTORAMENTO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(75));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(0));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CANDIDATURA_ESPECIALIZACAO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(50));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(0));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("TAXA_MATRICULA").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode("BOLONHA_INTEGRATED_MASTER_DEGREE"));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(25));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(0));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("RENOVACAO_INSCRICAO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode("BOLONHA_INTEGRATED_MASTER_DEGREE"));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(10));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(0));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("TAXA_MATRICULA").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode("BOLONHA_MASTER_DEGREE"));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(75));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(0));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("TAXA_MATRICULA").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode("BOLONHA_PHD"));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(75));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(0));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("PROCESSO_EQUIVALENCIA_RECONHECIMENTO_GRAU").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode("BOLONHA_INTEGRATED_MASTER_DEGREE"));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(500));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(0));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("PROCESSO_EQUIVALENCIA_RECONHECIMENTO_GRAU").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode("BOLONHA_MASTER_DEGREE"));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(550));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(0));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("PROCESSO_EQUIVALENCIA_RECONHECIMENTO_GRAU").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode("BOLONHA_PHD"));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(600));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(0));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CERTIDAO_EQUIVALENCIA_RECONHECIMENTO_GRAU").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(20));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("PROVAS_AVALIACAO_CAPACIDADE_M23_ADMISSAO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(60));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("PROVAS_AVALIACAO_CAPACIDADE_M23_RECLAMACAO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(30));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("PEDIDO_EQUIVALENCIA_CREDITACAO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(40));
//            bean.setUnitsForBase(6);
//            bean.setUnitAmount(new BigDecimal(5));
//            bean.setMaximumAmount(maximumAmount(250));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("PEDIDO_REGISTO_GRAUS_DL_341_2007").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(26));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("ADMISSAO_PROVAS_ACADEMICAS_DOUTORAMENTO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(500));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("ADMISSAO_PROVAS_ACADEMICAS_DOUTORAMENTO_ART_33_DL_74_2006").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(2500));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("ADMISSAO_PROVAS_ACADEMICAS_AGREGACAO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(600));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product =
//                    Product.findUniqueByCode("ADMISSAO_PROVAS_ACADEMICAS_HABILITACAO_COORDENACAO_CIENTIFICA").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(600));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("ADMISSAO_PROVAS_ACADEMICAS_MESTRADO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(180));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CERTIDAO_REGISTO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(38));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CERTIDAO_REGISTO_2_VIA").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(25));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("SUPLEMENTO_DIPLOMA_2_VIA").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(25));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CERTIDAO_REGISTO_CURSO_POS_GRADUADO_ESPECIALIZACAO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(38));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CERTIDAO_CONCLUSAO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(20));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(50));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CERTIDAO_PROVAS_APTIDAO_PEDAGOGICA").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(20));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(50));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CERTIDAO_OBTENCAO_TITULO_AGREGADO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(20));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(50));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF-DEPREC").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CERTIDAO_MATRICULA").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF-DEPREC");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(10));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(50));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF-DEPREC").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CERTIDAO_INSCRICAO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF-DEPREC");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(10));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(50));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF-DEPREC").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CERTIDAO_FREQUENCIA_EXAME").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF-DEPREC");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(10));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(50));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF-DEPREC").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CERTIDAO_CONDUTA_ACADEMICA").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF-DEPREC");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(10));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(50));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF-DEPREC").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CERTIFICADO_NARRATIVA_TEOR").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF-DEPREC");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(10));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF-DEPREC").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CERTIFICADO_AVALIACAO_CAPACIDADE_M23").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF-DEPREC");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(20));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CERTIDAO_CARGAS_HORARIAS_CONTEUDOS_PROGRAMATICOS").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(0));
//            bean.setUnitsForBase(0);
//            bean.setUnitAmount(new BigDecimal(7.5));
//            bean.setMaximumAmount(maximumAmount(150));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CERTIDAO_FOTOCOPIA").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(5));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(1));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("DECLARACAO_MATRICULA").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(10));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("DECLARACAO_INSCRICAO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(10));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CARTA_CURSO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode("BOLONHA_INTEGRATED_MASTER_DEGREE"));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(125));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CARTA_CURSO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode("BOLONHA_MASTER_DEGREE"));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(125));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CARTA_CURSO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode("BOLONHA_PHD"));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(175));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CARTA_CURSO_2_VIA").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(80));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("DIPLOMA_CURSO_DOUTORAMENTO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(80));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("DIPLOMA_CURSO_MESTRADO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(80));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("DIPLOMA_CURSO_ESPECIALIZACAO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(80));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CARTA_TITULO_AGREGACAO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(200));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CARTA_TITULO_HABILITACAO_COORDENACAO_CIENTIFICA").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(200));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("CARTA_TITULO_2_VIA").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(80));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("PRATICA_ATOS_FORA_PRAZO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(0));
//            bean.setUnitsForBase(0);
//            bean.setUnitAmount(new BigDecimal(4));
//            bean.setMaximumAmount(maximumAmount(120));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(0));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("AVERBAMENTO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(3));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("TAXA_MELHORIA").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(15));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(0));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("PEDIDO_PERMUTA").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(20));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("VALIDACAO_PROCESSOS_ACESSO_M23_OUTRAS_INSTITUICOES").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(60));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("FOTOCOPIA").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(1));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(0));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("PEDIDO_MUDANCA_TURMA").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(10));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(0));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("PEDIDO_MUDANCA_UNIDADE_CURRICULAR").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(10));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(0));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("REVISAO_PROVAS_CAUCAO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(20));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(0));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            final Product product = Product.findUniqueByCode("PLANO_INTEGRACAO_CURRICULAR_REINGRESSO").get();
//            final FinantialEntity finantialEntity = oneOfFinantialEntity("FF");
//            final AcademicTariffBean bean = new AcademicTariffBean();
//            bean.setAdministrativeOffice(AdministrativeOffice.readDegreeAdministrativeOffice());
//            bean.setBeginDate(parseLocalDate("1/07/2015"));
//            bean.setDegreeType(findDegreeTypeByCode(""));
//            bean.setDegree(findDegree(""));
//            bean.setBaseAmount(new BigDecimal(50));
//            bean.setUnitsForBase(1);
//            bean.setUnitAmount(new BigDecimal(0));
//            bean.setMaximumAmount(maximumAmount(-1));
//            bean.setDueDateCalculationType(DueDateCalculationType.DAYS_AFTER_CREATION);
//            bean.setFixedDueDate(fixedDueDate(""));
//            bean.setNumberOfDaysAfterCreationForDueDate(0);
//            bean.setUrgencyRate(new BigDecimal(100));
//            bean.setLanguageTranslationRate(new BigDecimal(0));
//            AcademicTariff.create(finantialEntity, product, bean);
//        }
//
//    }
//
//    private void createServiceRequestTypesToProducts_FROM_SPREADSHEET() {
//        if (!fromAcronymsToFinantialInstitutionList("FMD, FF").isEmpty()) {
//            ServiceRequestMapEntry
//                    .create(Product.findUniqueByCode("CERTIDAO_INSCRICAO").get(), ServiceRequestType.findUnique(
//                            AcademicServiceRequestType.DOCUMENT, DocumentRequestType.ENROLMENT_CERTIFICATE),
//                            AcademicServiceRequestSituationType.NEW, requestTypeDetailed(false));
//        }
//    }
//
//    private Set<ServiceRequestTypeOption> requestTypeDetailed(final boolean isDetailed) {
//
//        if (isDetailed) {
//            return Sets.newHashSet(ServiceRequestTypeOption.findDetailedOption().get());
//        }
//
//        return Sets.newHashSet();
//    }
//
//    private void createAcademicTaxes_FROM_SPREADSHEET() {
//
//        if (!fromAcronymsToFinantialInstitutionList("FMD, FF").isEmpty()) {
//            AcademicTax.create(Product.findUniqueByCode("SEGURO_ESCOLAR").get(), true, true, true, true);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FMD, FF").isEmpty()) {
//            AcademicTax.create(Product.findUniqueByCode("TAXA_MELHORIA").get(), true, true, true, false);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FL, FF").isEmpty()) {
//            AcademicTax.create(Product.findUniqueByCode("TAXA_INSCRICAO").get(), true, true, true, true);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            AcademicTax.create(Product.findUniqueByCode("TAXA_CANDIDATURA").get(), true, true, false, false);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            AcademicTax.create(Product.findUniqueByCode("TAXA_MATRICULA").get(), true, true, false, true);
//        }
//        if (!fromAcronymsToFinantialInstitutionList("FF").isEmpty()) {
//            AcademicTax.create(Product.findUniqueByCode("RENOVACAO_INSCRICAO").get(), true, false, true, true);
//        }
//    }
//
//    private void configureAcademicTreasurySettings_FROM_SPREADSHEET() {
//        if (!fromAcronymsToFinantialInstitutionList("FF, FL, FMD, FMV, RUL").isEmpty()) {
//            AcademicTreasurySettings.getInstance().edit(ProductGroup.findByCode("EMOLUMENT"), ProductGroup.findByCode("TUITION"),
//                    AcademicTax.findUnique(Product.findUniqueByCode("TAXA_MELHORIA").get()).get(), true);
//        }
//    }
//
//    private void createTuitionPaymentPlanGroups_FROM_SPREADSHEET() {
//
//        final Product REGISTRATION_TUITION_product = Product.findUniqueByCode("PROPINA_MATRICULA").get();
//        final Product STANDALONE_TUITION_product = Product.findUniqueByCode("PROPINA_UNIDADES_CURRICULARES_ISOLADAS").get();
//        final Product EXTRACURRICULAR_TUITION_product = Product.findUniqueByCode("PROPINA_UNIDADES_EXTRACURRICULARES").get();
//
//        final LocalizedString REGISTRATION_TUITION_name =
//                new LocalizedString(pt(), "Propina de Matrícula").with(en(), "Registration Tuition");
//        final LocalizedString STANDALONE_TUITION_name =
//                new LocalizedString(pt(), "Propina em Unidades Curriculares Isoladas").with(en(), "Standalone Tuition");
//        final LocalizedString EXTRACURRICULAR_TUITION_name =
//                new LocalizedString(pt(), "Propina em Unidades Extracurriculares").with(en(), "Extracurricular Tuition");
//
//        TuitionPaymentPlanGroup.create("REGISTRATION_TUITION", REGISTRATION_TUITION_name, true, false, false,
//                REGISTRATION_TUITION_product);
//        TuitionPaymentPlanGroup.create("STANDALONE_TUITION", STANDALONE_TUITION_name, false, true, false,
//                STANDALONE_TUITION_product);
//        TuitionPaymentPlanGroup.create("EXTRACURRICULAR_TUITION", EXTRACURRICULAR_TUITION_name, false, false, true,
//                EXTRACURRICULAR_TUITION_product);
//    }
//
//    private List<FinantialInstitution> fromAcronymsToFinantialInstitutionList(final String acronyms) {
//        String[] split = acronyms.split(",");
//
//        final List<FinantialInstitution> result = new ArrayList<FinantialInstitution>();
//        for (String acronym : split) {
//            if (!FI_LOOKUP.containsKey(acronym.trim())) {
//                continue;
//            }
//
//            result.add(FinantialInstitution.findUniqueByFiscalCode(FI_LOOKUP.get(acronym.trim())).get());
//        }
//
//        return result;
//    }
//
//    private FinantialEntity oneOfFinantialEntity(final String acronyms) {
//        return FinantialEntity.findAll()
//                .filter(l -> l.getAdministrativeOffice() == AdministrativeOffice.readDegreeAdministrativeOffice()).findFirst()
//                .get();
//    }
//
//    private DegreeType findDegreeTypeByCode(final String code) {
//        if (code.isEmpty()) {
//            return null;
//        }
//
//        for (DegreeType degreeType : Bennu.getInstance().getDegreeTypeSet()) {
//            if (code.equals(degreeType.getCode())) {
//                return degreeType;
//            }
//        }
//
//        return null;
//    }
//
//    private Degree findDegree(final String code) {
//        if (code.isEmpty()) {
//            return null;
//        }
//
//        return Degree.find(code);
//    }
//
//    private BigDecimal maximumAmount(int v) {
//        if (v == NOT_APPLIED) {
//            return BigDecimal.ZERO;
//        }
//
//        return new BigDecimal(v);
//    }
//
//    private org.joda.time.LocalDate fixedDueDate(final String v) {
//        if (v.isEmpty()) {
//            return null;
//        }
//
//        return DateTimeFormat.forPattern("dd/MM/yyyy").parseLocalDate(v);
//    }
//
//    private org.joda.time.LocalDate parseLocalDate(final String v) {
//        if (v.isEmpty()) {
//            return null;
//        }
//
//        return DateTimeFormat.forPattern("dd/MM/yyyy").parseLocalDate(v);
//    }
//
//    private BigDecimal amount(final String v) {
//        if (v.isEmpty()) {
//            return null;
//        }
//
//        return new BigDecimal(v);
//    }
//
//    private Locale pt() {
//        return new Locale("PT", "pt");
//    }
//
//    private Locale en() {
//        return new Locale("EN", "en");
//    }
//
//    private LocalizedString defaultUnitOfMeasure() {
//        return new LocalizedString(pt(), "Unidade").with(en(), "Unit");
//    }
//
//    private VatType defaultVatType() {
//        return VatType.findByCode("ISE");
//    }
//
//    private static void createDefaultServiceRequestTypes() {
//        ServiceRequestTypeOption.create(
//                "DETAILED",
//                BundleUtil.getLocalizedString("resources.AcademicAdminOffice", ServiceRequestTypeOption.class.getSimpleName()
//                        + ".detailed"), true);
//
//        for (final AcademicServiceRequestType academicServiceRequestType : AcademicServiceRequestType.values()) {
//            if (academicServiceRequestType == AcademicServiceRequestType.DOCUMENT) {
//                continue;
//            } else if (academicServiceRequestType == AcademicServiceRequestType.DIPLOMA_REQUEST) {
//                ServiceRequestType.createLegacy(academicServiceRequestType.name(), new LocalizedString(new Locale("PT", "pt"),
//                        academicServiceRequestType.getLocalizedName()), false, academicServiceRequestType,
//                        DocumentRequestType.DIPLOMA_REQUEST, true, ServiceRequestCategory.SERVICES);
//                continue;
//            } else if (academicServiceRequestType == AcademicServiceRequestType.DIPLOMA_SUPPLEMENT_REQUEST) {
//                ServiceRequestType.createLegacy(academicServiceRequestType.name(), new LocalizedString(new Locale("PT", "pt"),
//                        academicServiceRequestType.getLocalizedName()), false, academicServiceRequestType,
//                        DocumentRequestType.DIPLOMA_SUPPLEMENT_REQUEST, true, ServiceRequestCategory.SERVICES);
//                continue;
//            } else if (academicServiceRequestType == AcademicServiceRequestType.REGISTRY_DIPLOMA_REQUEST) {
//                ServiceRequestType.createLegacy(academicServiceRequestType.name(), new LocalizedString(new Locale("PT", "pt"),
//                        academicServiceRequestType.getLocalizedName()), false, academicServiceRequestType,
//                        DocumentRequestType.REGISTRY_DIPLOMA_REQUEST, true, ServiceRequestCategory.SERVICES);
//                continue;
//            }
//
//            ServiceRequestType.createLegacy(academicServiceRequestType.name(), new LocalizedString(new Locale("PT", "pt"),
//                    academicServiceRequestType.getLocalizedName()), false, academicServiceRequestType, null, true,
//                    ServiceRequestCategory.SERVICES);
//        }
//
//        for (final DocumentRequestType documentRequestType : DocumentRequestType.values()) {
//
//            if (documentRequestType == DocumentRequestType.DIPLOMA_REQUEST) {
//                continue;
//            } else if (documentRequestType == DocumentRequestType.DIPLOMA_SUPPLEMENT_REQUEST) {
//                continue;
//            } else if (documentRequestType == DocumentRequestType.REGISTRY_DIPLOMA_REQUEST) {
//                continue;
//            }
//
//            ServiceRequestType.createLegacy(
//                    documentRequestType.name(),
//                    BundleUtil.getLocalizedString("resources.EnumerationResources",
//                            "DocumentRequestType." + documentRequestType.name()), false, AcademicServiceRequestType.DOCUMENT,
//                    documentRequestType, true, ServiceRequestCategory.SERVICES);
//        }
//    }
//
//    private void createProducts_FROM_SPREADSHEET() {
//
//        final ProductGroup TUITION_productGroup = ProductGroup.findByCode("TUITION");
//        final ProductGroup EMOLUMENT_productGroup = ProductGroup.findByCode("EMOLUMENT");
//        final ProductGroup OTHER_productGroup = ProductGroup.findByCode("OTHER");
//
//        final LocalizedString PROPINA_MATRICULA_name =
//                new LocalizedString(pt(), "Propina de Matrícula").with(en(), "Registration Tuition");
//        Product.create(TUITION_productGroup, "PROPINA_MATRICULA", PROPINA_MATRICULA_name, defaultUnitOfMeasure(), true,
//                defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FF"));
//        final LocalizedString PROPINA_MATRICULA_1_PRESTACAO_name =
//                new LocalizedString(pt(), "1º Prestação da Propina de Matrícula ").with(en(), "");
//        Product.create(TUITION_productGroup, "PROPINA_MATRICULA_1_PRESTACAO", PROPINA_MATRICULA_1_PRESTACAO_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FF"));
//        final LocalizedString PROPINA_MATRICULA_2_PRESTACAO_name =
//                new LocalizedString(pt(), "2º Prestação da Propina de Matrícula").with(en(), "");
//        Product.create(TUITION_productGroup, "PROPINA_MATRICULA_2_PRESTACAO", PROPINA_MATRICULA_2_PRESTACAO_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FF"));
//        final LocalizedString PROPINA_MATRICULA_3_PRESTACAO_name =
//                new LocalizedString(pt(), "3º Prestação da Propina de Matrícula").with(en(), "");
//        Product.create(TUITION_productGroup, "PROPINA_MATRICULA_3_PRESTACAO", PROPINA_MATRICULA_3_PRESTACAO_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FF"));
//        final LocalizedString PROPINA_MATRICULA_4_PRESTACAO_name =
//                new LocalizedString(pt(), "4º Prestação da Propina de Matrícula").with(en(), "");
//        Product.create(TUITION_productGroup, "PROPINA_MATRICULA_4_PRESTACAO", PROPINA_MATRICULA_4_PRESTACAO_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FF"));
//        final LocalizedString PROPINA_MATRICULA_5_PRESTACAO_name =
//                new LocalizedString(pt(), "5º Prestação da Propina de Matrícula").with(en(), "");
//        Product.create(TUITION_productGroup, "PROPINA_MATRICULA_5_PRESTACAO", PROPINA_MATRICULA_5_PRESTACAO_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FF"));
//        final LocalizedString PROPINA_MATRICULA_6_PRESTACAO_name =
//                new LocalizedString(pt(), "6º Prestação da Propina de Matrícula").with(en(), "");
//        Product.create(TUITION_productGroup, "PROPINA_MATRICULA_6_PRESTACAO", PROPINA_MATRICULA_6_PRESTACAO_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FF"));
//        final LocalizedString PROPINA_MATRICULA_7_PRESTACAO_name =
//                new LocalizedString(pt(), "7º Prestação da Propina de Matrícula").with(en(), "");
//        Product.create(TUITION_productGroup, "PROPINA_MATRICULA_7_PRESTACAO", PROPINA_MATRICULA_7_PRESTACAO_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FF"));
//        final LocalizedString PROPINA_MATRICULA_8_PRESTACAO_name =
//                new LocalizedString(pt(), "8º Prestação da Propina de Matrícula").with(en(), "");
//        Product.create(TUITION_productGroup, "PROPINA_MATRICULA_8_PRESTACAO", PROPINA_MATRICULA_8_PRESTACAO_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FF"));
//        final LocalizedString PROPINA_MATRICULA_9_PRESTACAO_name =
//                new LocalizedString(pt(), "9º Prestação da Propina de Matrícula").with(en(), "");
//        Product.create(TUITION_productGroup, "PROPINA_MATRICULA_9_PRESTACAO", PROPINA_MATRICULA_9_PRESTACAO_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FF"));
//        final LocalizedString PROPINA_UNIDADES_CURRICULARES_ISOLADAS_name =
//                new LocalizedString(pt(), "Propina em Unidades Curriculares Isoladas").with(en(), "");
//        Product.create(TUITION_productGroup, "PROPINA_UNIDADES_CURRICULARES_ISOLADAS",
//                PROPINA_UNIDADES_CURRICULARES_ISOLADAS_name, defaultUnitOfMeasure(), true, defaultVatType(),
//                fromAcronymsToFinantialInstitutionList("FMD, FF"));
//        final LocalizedString PROPINA_UNIDADES_EXTRACURRICULARES_name =
//                new LocalizedString(pt(), "Propina em Unidades Extracurriculares").with(en(), "");
//        Product.create(TUITION_productGroup, "PROPINA_UNIDADES_EXTRACURRICULARES", PROPINA_UNIDADES_EXTRACURRICULARES_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FF"));
//        final LocalizedString SEGURO_ESCOLAR_name = new LocalizedString(pt(), "Seguro Escolar").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "SEGURO_ESCOLAR", SEGURO_ESCOLAR_name, defaultUnitOfMeasure(), true,
//                defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FF"));
//        final LocalizedString CARTA_CURSO_name = new LocalizedString(pt(), "Carta de Curso").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CARTA_CURSO", CARTA_CURSO_name, defaultUnitOfMeasure(), true, defaultVatType(),
//                fromAcronymsToFinantialInstitutionList("FMD, FMV, FL, FF, RUL"));
//        final LocalizedString CARTA_CURSO_2_VIA_name = new LocalizedString(pt(), "Carta de Curso - 2º Via").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CARTA_CURSO_2_VIA", CARTA_CURSO_2_VIA_name, defaultUnitOfMeasure(), true,
//                defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FMV, FL, FF, RUL"));
//        final LocalizedString CARTA_TITULO_AGREGACAO_name =
//                new LocalizedString(pt(), "Carta de Título - Agregação").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CARTA_TITULO_AGREGACAO", CARTA_TITULO_AGREGACAO_name, defaultUnitOfMeasure(),
//                true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FMV, FL, FF, RUL"));
//        final LocalizedString CARTA_TITULO_HABILITACAO_COORDENACAO_CIENTIFICA_name =
//                new LocalizedString(pt(),
//                        "Carta de Título - Habilitação para o Exercício de Actividades de Coordenação Científica").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CARTA_TITULO_HABILITACAO_COORDENACAO_CIENTIFICA",
//                CARTA_TITULO_HABILITACAO_COORDENACAO_CIENTIFICA_name, defaultUnitOfMeasure(), true, defaultVatType(),
//                fromAcronymsToFinantialInstitutionList("FMD, FMV, FL, FF, RUL"));
//        final LocalizedString CARTA_TITULO_2_VIA_name = new LocalizedString(pt(), "Carta de Título - 2º Via").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CARTA_TITULO_2_VIA", CARTA_TITULO_2_VIA_name, defaultUnitOfMeasure(), true,
//                defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FMV, FL, FF, RUL"));
//        final LocalizedString PROCESSO_EQUIVALENCIA_RECONHECIMENTO_GRAU_name =
//                new LocalizedString(pt(), "Processos de Equivalência e Reconhecimento de Grau").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "PROCESSO_EQUIVALENCIA_RECONHECIMENTO_GRAU",
//                PROCESSO_EQUIVALENCIA_RECONHECIMENTO_GRAU_name, defaultUnitOfMeasure(), true, defaultVatType(),
//                fromAcronymsToFinantialInstitutionList("FMD, FMV, FL, FF, RUL"));
//        final LocalizedString CERTIDAO_EQUIVALENCIA_RECONHECIMENTO_GRAU_name =
//                new LocalizedString(pt(), "Certidão de Equivalência ou Reconhecimento de licenciatura, mestrado ou doutoramento")
//                        .with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CERTIDAO_EQUIVALENCIA_RECONHECIMENTO_GRAU",
//                CERTIDAO_EQUIVALENCIA_RECONHECIMENTO_GRAU_name, defaultUnitOfMeasure(), true, defaultVatType(),
//                fromAcronymsToFinantialInstitutionList("FMD, FMV, FL, FF, RUL"));
//        final LocalizedString PEDIDO_REGISTO_GRAUS_DL_341_2007_name =
//                new LocalizedString(pt(), "Pedido de Registo de Graus abrangidos pelo De-creto-Lei n.º 341/2007, de 12 outubro")
//                        .with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "PEDIDO_REGISTO_GRAUS_DL_341_2007", PEDIDO_REGISTO_GRAUS_DL_341_2007_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FMV, FL, FF, RUL"));
//        final LocalizedString PROVAS_AVALIACAO_CAPACIDADE_M23_ADMISSAO_name =
//                new LocalizedString(
//                        pt(),
//                        "Provas de avaliação da capacidade para frequência do Ensino Superior de Maiores de 23 anos, realizadas pelos Serviços Centrais da Ulisboa - M23 — Admissão a provas")
//                        .with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "PROVAS_AVALIACAO_CAPACIDADE_M23_ADMISSAO",
//                PROVAS_AVALIACAO_CAPACIDADE_M23_ADMISSAO_name, defaultUnitOfMeasure(), true, defaultVatType(),
//                fromAcronymsToFinantialInstitutionList("FMD, FMV, FL, FF, RUL"));
//        final LocalizedString PROVAS_AVALIACAO_CAPACIDADE_M23_RECLAMACAO_name =
//                new LocalizedString(
//                        pt(),
//                        "Provas de avaliação da capacidade para frequência do Ensino Superior de Maiores de 23 anos, realizadas pelos Serviços Centrais da Ulisboa - M23 — Reclamação da classificação das provas")
//                        .with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "PROVAS_AVALIACAO_CAPACIDADE_M23_RECLAMACAO",
//                PROVAS_AVALIACAO_CAPACIDADE_M23_RECLAMACAO_name, defaultUnitOfMeasure(), true, defaultVatType(),
//                fromAcronymsToFinantialInstitutionList("FMD, FMV, FL, FF, RUL"));
//        final LocalizedString CERTIDAO_REGISTO_name = new LocalizedString(pt(), "Certidão de Registo").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CERTIDAO_REGISTO", CERTIDAO_REGISTO_name, defaultUnitOfMeasure(), true,
//                defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FMV, FL, FF, RUL"));
//        final LocalizedString CERTIDAO_REGISTO_2_VIA_name =
//                new LocalizedString(pt(), "Certidão de Registo - 2º Via").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CERTIDAO_REGISTO_2_VIA", CERTIDAO_REGISTO_2_VIA_name, defaultUnitOfMeasure(),
//                true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FMV, FL, FF, RUL"));
//        final LocalizedString SUPLEMENTO_DIPLOMA_2_VIA_name =
//                new LocalizedString(pt(), "Suplemento ao Diploma - 2º Via").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "SUPLEMENTO_DIPLOMA_2_VIA", SUPLEMENTO_DIPLOMA_2_VIA_name, defaultUnitOfMeasure(),
//                true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FMV, FL, FF, RUL"));
//        final LocalizedString CERTIDAO_REGISTO_CURSO_POS_GRADUADO_ESPECIALIZACAO_name =
//                new LocalizedString(pt(), "Certidão de Registo de Curso pós-graduado de especialização").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CERTIDAO_REGISTO_CURSO_POS_GRADUADO_ESPECIALIZACAO",
//                CERTIDAO_REGISTO_CURSO_POS_GRADUADO_ESPECIALIZACAO_name, defaultUnitOfMeasure(), true, defaultVatType(),
//                fromAcronymsToFinantialInstitutionList("FMD, FL, FF"));
//        final LocalizedString DIPLOMA_CURSO_DOUTORAMENTO_name =
//                new LocalizedString(pt(), "Diploma — Curso de doutoramento (componente curricular) ").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "DIPLOMA_CURSO_DOUTORAMENTO", DIPLOMA_CURSO_DOUTORAMENTO_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FL, FF"));
//        final LocalizedString DIPLOMA_CURSO_MESTRADO_name =
//                new LocalizedString(pt(), "Diploma — Curso de mestrado (componente curricular)").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "DIPLOMA_CURSO_MESTRADO", DIPLOMA_CURSO_MESTRADO_name, defaultUnitOfMeasure(),
//                true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FL, FF"));
//        final LocalizedString DIPLOMA_CURSO_ESPECIALIZACAO_name =
//                new LocalizedString(pt(), "Diploma — Curso de especialização").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "DIPLOMA_CURSO_ESPECIALIZACAO", DIPLOMA_CURSO_ESPECIALIZACAO_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FL, FF"));
//        final LocalizedString ADMISSAO_PROVAS_ACADEMICAS_DOUTORAMENTO_name =
//                new LocalizedString(pt(), "Admissão a Provas Académicas — Doutoramento").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "ADMISSAO_PROVAS_ACADEMICAS_DOUTORAMENTO",
//                ADMISSAO_PROVAS_ACADEMICAS_DOUTORAMENTO_name, defaultUnitOfMeasure(), true, defaultVatType(),
//                fromAcronymsToFinantialInstitutionList("FMD, FL, FF"));
//        final LocalizedString ADMISSAO_PROVAS_ACADEMICAS_DOUTORAMENTO_ART_33_DL_74_2006_name =
//                new LocalizedString(
//                        pt(),
//                        "Admissão a Provas Académicas — Doutoramento ao abrigo do artigo 33.º do Decreto-Lei n.º 74/2006, de 24 de março, alterado pelos Decretos-Leis n.os 107/208, de 25 de junho, 230/2009, de 14 de setembro e 115/2013, de 7 de agosto")
//                        .with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "ADMISSAO_PROVAS_ACADEMICAS_DOUTORAMENTO_ART_33_DL_74_2006",
//                ADMISSAO_PROVAS_ACADEMICAS_DOUTORAMENTO_ART_33_DL_74_2006_name, defaultUnitOfMeasure(), true, defaultVatType(),
//                fromAcronymsToFinantialInstitutionList("FMD, FL, FF"));
//        final LocalizedString ADMISSAO_PROVAS_ACADEMICAS_AGREGACAO_name =
//                new LocalizedString(pt(), "Admissão a Provas Académicas — Agregação").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "ADMISSAO_PROVAS_ACADEMICAS_AGREGACAO", ADMISSAO_PROVAS_ACADEMICAS_AGREGACAO_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FL, FF"));
//        final LocalizedString ADMISSAO_PROVAS_ACADEMICAS_HABILITACAO_COORDENACAO_CIENTIFICA_name =
//                new LocalizedString(pt(),
//                        "Admissão a Provas Académicas — Habilitação para o Exercício de Atividades de Coordenação Científica")
//                        .with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "ADMISSAO_PROVAS_ACADEMICAS_HABILITACAO_COORDENACAO_CIENTIFICA",
//                ADMISSAO_PROVAS_ACADEMICAS_HABILITACAO_COORDENACAO_CIENTIFICA_name, defaultUnitOfMeasure(), true,
//                defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FL, FF"));
//        final LocalizedString ADMISSAO_PROVAS_ACADEMICAS_MESTRADO_name =
//                new LocalizedString(pt(), "Admissão a Provas Académicas — Mestrado ").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "ADMISSAO_PROVAS_ACADEMICAS_MESTRADO", ADMISSAO_PROVAS_ACADEMICAS_MESTRADO_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FL, FF"));
//        final LocalizedString CERTIDAO_CONCLUSAO_name = new LocalizedString(pt(), "Certidão de Conclusão").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CERTIDAO_CONCLUSAO", CERTIDAO_CONCLUSAO_name, defaultUnitOfMeasure(), true,
//                defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FL, FF"));
//        final LocalizedString CERTIDAO_PROVAS_APTIDAO_PEDAGOGICA_name =
//                new LocalizedString(pt(), "Certidão de Provas de Aptidão Pedagógica e Capacidade Ciêntifica").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CERTIDAO_PROVAS_APTIDAO_PEDAGOGICA", CERTIDAO_PROVAS_APTIDAO_PEDAGOGICA_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FL, FF"));
//        final LocalizedString CERTIDAO_OBTENCAO_TITULO_AGREGADO_name =
//                new LocalizedString(pt(), "Certidão de obtenção do título de agregado e das respectivas capacidades ciêntificas")
//                        .with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CERTIDAO_OBTENCAO_TITULO_AGREGADO", CERTIDAO_OBTENCAO_TITULO_AGREGADO_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FL, FF"));
//        final LocalizedString CERTIDAO_MATRICULA_name = new LocalizedString(pt(), "Certidão de Matrícula").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CERTIDAO_MATRICULA", CERTIDAO_MATRICULA_name, defaultUnitOfMeasure(), true,
//                defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FL, FF"));
//        final LocalizedString CERTIDAO_INSCRICAO_name = new LocalizedString(pt(), "Certidão de Inscrição").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CERTIDAO_INSCRICAO", CERTIDAO_INSCRICAO_name, defaultUnitOfMeasure(), true,
//                defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FL, FF"));
//        final LocalizedString CERTIDAO_FREQUENCIA_EXAME_name = new LocalizedString(pt(), "Certidão de Frequência").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CERTIDAO_FREQUENCIA_EXAME", CERTIDAO_FREQUENCIA_EXAME_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FL, FF"));
//        final LocalizedString CERTIDAO_CONDUTA_ACADEMICA_name =
//                new LocalizedString(pt(), "Certidão de Conduta Académica").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CERTIDAO_CONDUTA_ACADEMICA", CERTIDAO_CONDUTA_ACADEMICA_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FL, FF"));
//        final LocalizedString CERTIFICADO_NARRATIVA_TEOR_name =
//                new LocalizedString(pt(), "Certificado de narrativa ou de teor").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CERTIFICADO_NARRATIVA_TEOR", CERTIFICADO_NARRATIVA_TEOR_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FL, FF"));
//        final LocalizedString CERTIFICADO_AVALIACAO_CAPACIDADE_M23_name =
//                new LocalizedString(pt(),
//                        "Certificado de aprovação no processo de ava-liação da capacidade para a frequência do Ensino Superior de Maiores de 23 anos")
//                        .with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CERTIFICADO_AVALIACAO_CAPACIDADE_M23", CERTIFICADO_AVALIACAO_CAPACIDADE_M23_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FF"));
//        final LocalizedString CERTIDAO_CARGAS_HORARIAS_CONTEUDOS_PROGRAMATICOS_name =
//                new LocalizedString(pt(), "Certificado de cargas horárias e conteúdos programáticos").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CERTIDAO_CARGAS_HORARIAS_CONTEUDOS_PROGRAMATICOS",
//                CERTIDAO_CARGAS_HORARIAS_CONTEUDOS_PROGRAMATICOS_name, defaultUnitOfMeasure(), true, defaultVatType(),
//                fromAcronymsToFinantialInstitutionList("FMD, FL, FF"));
//        final LocalizedString CERTIDAO_FOTOCOPIA_name = new LocalizedString(pt(), "Certidão por Fotocópia").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CERTIDAO_FOTOCOPIA", CERTIDAO_FOTOCOPIA_name, defaultUnitOfMeasure(), true,
//                defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FF"));
//        final LocalizedString PEDIDO_CREDITACAO_CONHECIMENTOS_COMPETENCIAS_name =
//                new LocalizedString(pt(), "Pedido de Creditação de Conhecimentos e Competências").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "PEDIDO_CREDITACAO_CONHECIMENTOS_COMPETENCIAS",
//                PEDIDO_CREDITACAO_CONHECIMENTOS_COMPETENCIAS_name, defaultUnitOfMeasure(), true, defaultVatType(),
//                fromAcronymsToFinantialInstitutionList("FMD, FF"));
//        final LocalizedString CANDIDATURA_REINGRESSO_name = new LocalizedString(pt(), "Candidatura: Reingresso").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CANDIDATURA_REINGRESSO", CANDIDATURA_REINGRESSO_name, defaultUnitOfMeasure(),
//                true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FL"));
//        final LocalizedString CANDIDATURA_TRANSFERENCIA_name =
//                new LocalizedString(pt(), "Candidatura: Transferência").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CANDIDATURA_TRANSFERENCIA", CANDIDATURA_TRANSFERENCIA_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FL"));
//        final LocalizedString CANDIDATURA_MUDANCA_CURSO_name =
//                new LocalizedString(pt(), "Candidatura: Mudança de Curso").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CANDIDATURA_MUDANCA_CURSO", CANDIDATURA_MUDANCA_CURSO_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FL"));
//        final LocalizedString CANDIDATURA_MAIORES_23_ANOS_name =
//                new LocalizedString(pt(), "Candidatura: Maiores de 23 anos").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CANDIDATURA_MAIORES_23_ANOS", CANDIDATURA_MAIORES_23_ANOS_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FL, FF"));
//        final LocalizedString CANDIDATURA_OUTRO_name =
//                new LocalizedString(pt(), "Candidatura: Outro concurso especial de acesso").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CANDIDATURA_OUTRO", CANDIDATURA_OUTRO_name, defaultUnitOfMeasure(), true,
//                defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FL, FF"));
//        final LocalizedString CANDIDATURA_REGIME_LIVRE_name =
//                new LocalizedString(pt(), "Candidatura: Regime Livre (UC Isoladas)").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CANDIDATURA_REGIME_LIVRE", CANDIDATURA_REGIME_LIVRE_name, defaultUnitOfMeasure(),
//                true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD"));
//        final LocalizedString CANDIDATURA_CURSO_APERFEICOAMENTO_name =
//                new LocalizedString(pt(), "Candidatura: Curso de Aperfeiçoamento").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CANDIDATURA_CURSO_APERFEICOAMENTO", CANDIDATURA_CURSO_APERFEICOAMENTO_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD"));
//        final LocalizedString CANDIDATURA_CURSO_B_LEARNING_name =
//                new LocalizedString(pt(), "Candidatura: Curso B-Learning").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CANDIDATURA_CURSO_B_LEARNING", CANDIDATURA_CURSO_B_LEARNING_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD"));
//        final LocalizedString CANDIDATURA_ESPECIALIZACAO_name =
//                new LocalizedString(pt(), "Candidatura: Curso Especialização").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CANDIDATURA_ESPECIALIZACAO", CANDIDATURA_ESPECIALIZACAO_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FF"));
//        final LocalizedString CANDIDATURA_MESTRADO_name = new LocalizedString(pt(), "Candidatura: Mestrado").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CANDIDATURA_MESTRADO", CANDIDATURA_MESTRADO_name, defaultUnitOfMeasure(), true,
//                defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FL, FF"));
//        final LocalizedString CANDIDATURA_DOUTORAMENTO_name =
//                new LocalizedString(pt(), "Candidatura: Doutoramento").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CANDIDATURA_DOUTORAMENTO", CANDIDATURA_DOUTORAMENTO_name, defaultUnitOfMeasure(),
//                true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FL, FF"));
//        final LocalizedString PRATICA_ATOS_FORA_PRAZO_name =
//                new LocalizedString(pt(), "Prática de Atos Fora do Prazo").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "PRATICA_ATOS_FORA_PRAZO", PRATICA_ATOS_FORA_PRAZO_name, defaultUnitOfMeasure(),
//                true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FL"));
//        final LocalizedString AVERBAMENTO_name = new LocalizedString(pt(), "Averbamento").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "AVERBAMENTO", AVERBAMENTO_name, defaultUnitOfMeasure(), true, defaultVatType(),
//                fromAcronymsToFinantialInstitutionList("FMD, FF"));
//        final LocalizedString TAXA_MELHORIA_name = new LocalizedString(pt(), "Taxa para Melhoria").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "TAXA_MELHORIA", TAXA_MELHORIA_name, defaultUnitOfMeasure(), true,
//                defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FL, FF"));
//        final LocalizedString PEDIDO_PERMUTA_name = new LocalizedString(pt(), "Pedido de permuta").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "PEDIDO_PERMUTA", PEDIDO_PERMUTA_name, defaultUnitOfMeasure(), true,
//                defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD, FF"));
//        final LocalizedString VALIDACAO_PROCESSOS_ACESSO_M23_OUTRAS_INSTITUICOES_name =
//                new LocalizedString(pt(),
//                        "Validação de processos de acesso de Maiores de 23 anos realizados em outras Instituições de Ensino Superior")
//                        .with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "VALIDACAO_PROCESSOS_ACESSO_M23_OUTRAS_INSTITUICOES",
//                VALIDACAO_PROCESSOS_ACESSO_M23_OUTRAS_INSTITUICOES_name, defaultUnitOfMeasure(), true, defaultVatType(),
//                fromAcronymsToFinantialInstitutionList("FMD, FF"));
//        final LocalizedString FOTOCOPIA_name = new LocalizedString(pt(), "Fotocópia").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "FOTOCOPIA", FOTOCOPIA_name, defaultUnitOfMeasure(), true, defaultVatType(),
//                fromAcronymsToFinantialInstitutionList("FMD, FL, FF"));
//        final LocalizedString TAXA_ENVIO_CORREIO_name = new LocalizedString(pt(), "Taxa de envio por correio  ").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "TAXA_ENVIO_CORREIO", TAXA_ENVIO_CORREIO_name, defaultUnitOfMeasure(), true,
//                defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD"));
//        final LocalizedString TAXA_DEVOLUCAO_CHEQUE_name =
//                new LocalizedString(pt(), "Taxa por devolução de cheque  ").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "TAXA_DEVOLUCAO_CHEQUE", TAXA_DEVOLUCAO_CHEQUE_name, defaultUnitOfMeasure(), true,
//                defaultVatType(), fromAcronymsToFinantialInstitutionList("FMD"));
//        final LocalizedString CANDIDATURA_CURSOS_NAO_CONFERENTES_GRAU_name =
//                new LocalizedString(pt(), "Candidatura: Cursos não conferentes de grau").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CANDIDATURA_CURSOS_NAO_CONFERENTES_GRAU",
//                CANDIDATURA_CURSOS_NAO_CONFERENTES_GRAU_name, defaultUnitOfMeasure(), true, defaultVatType(),
//                fromAcronymsToFinantialInstitutionList("FL"));
//        final LocalizedString PEDIDO_EQUIVALENCIA_CREDITACAO_name =
//                new LocalizedString(pt(), "Pedido de Equivalência").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "PEDIDO_EQUIVALENCIA_CREDITACAO", PEDIDO_EQUIVALENCIA_CREDITACAO_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FL, FF"));
//        final LocalizedString TAXA_MATRICULA_name = new LocalizedString(pt(), "Taxa de Matrícula").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "TAXA_MATRICULA", TAXA_MATRICULA_name, defaultUnitOfMeasure(), true,
//                defaultVatType(), fromAcronymsToFinantialInstitutionList("FL, FF"));
//        final LocalizedString TAXA_INSCRICAO_name = new LocalizedString(pt(), "Taxa de Inscrição").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "TAXA_INSCRICAO", TAXA_INSCRICAO_name, defaultUnitOfMeasure(), true,
//                defaultVatType(), fromAcronymsToFinantialInstitutionList("FL, FF"));
//        final LocalizedString TAXA_RENOVACAO_INSCRICAO_name =
//                new LocalizedString(pt(), "Taxa de Renovação de Inscrição").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "TAXA_RENOVACAO_INSCRICAO", TAXA_RENOVACAO_INSCRICAO_name, defaultUnitOfMeasure(),
//                true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FL"));
//        final LocalizedString DECLARACAO_MATRICULA_name = new LocalizedString(pt(), "Declaração de Matrícula").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "DECLARACAO_MATRICULA", DECLARACAO_MATRICULA_name, defaultUnitOfMeasure(), true,
//                defaultVatType(), fromAcronymsToFinantialInstitutionList("FL, FF"));
//        final LocalizedString DECLARACAO_INSCRICAO_name = new LocalizedString(pt(), "Declaracão de Inscrição").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "DECLARACAO_INSCRICAO", DECLARACAO_INSCRICAO_name, defaultUnitOfMeasure(), true,
//                defaultVatType(), fromAcronymsToFinantialInstitutionList("FF"));
//        final LocalizedString PEDIDO_MUDANCA_TURMA_name = new LocalizedString(pt(), "Pedido de Mudança de Turma").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "PEDIDO_MUDANCA_TURMA", PEDIDO_MUDANCA_TURMA_name, defaultUnitOfMeasure(), true,
//                defaultVatType(), fromAcronymsToFinantialInstitutionList("FL, FF"));
//        final LocalizedString PEDIDO_MUDANCA_UNIDADE_CURRICULAR_name =
//                new LocalizedString(pt(), "Pedido de Mudança de Unidade Curricular").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "PEDIDO_MUDANCA_UNIDADE_CURRICULAR", PEDIDO_MUDANCA_UNIDADE_CURRICULAR_name,
//                defaultUnitOfMeasure(), true, defaultVatType(), fromAcronymsToFinantialInstitutionList("FL, FF"));
//        final LocalizedString REVISAO_PROVAS_CAUCAO_name = new LocalizedString(pt(), "Revisão de provas — caução").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "REVISAO_PROVAS_CAUCAO", REVISAO_PROVAS_CAUCAO_name, defaultUnitOfMeasure(), true,
//                defaultVatType(), fromAcronymsToFinantialInstitutionList("FL, FF"));
//        final LocalizedString PLANO_INTEGRACAO_CURRICULAR_REINGRESSO_name =
//                new LocalizedString(pt(),
//                        "Plano de Integração Curricular devido a interrupção de estudos ocorrida antes de 2006/2007 (reingresso)")
//                        .with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "PLANO_INTEGRACAO_CURRICULAR_REINGRESSO",
//                PLANO_INTEGRACAO_CURRICULAR_REINGRESSO_name, defaultUnitOfMeasure(), true, defaultVatType(),
//                fromAcronymsToFinantialInstitutionList("FL, FF"));
//        final LocalizedString TAXA_PROCESSO_ADMINISTRATIVO_APLICADO_ALUNOS_INCOMING_name =
//                new LocalizedString(pt(), "Taxa de processo administrativo aplicável a alunos incoming").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "TAXA_PROCESSO_ADMINISTRATIVO_APLICADO_ALUNOS_INCOMING",
//                TAXA_PROCESSO_ADMINISTRATIVO_APLICADO_ALUNOS_INCOMING_name, defaultUnitOfMeasure(), true, defaultVatType(),
//                fromAcronymsToFinantialInstitutionList("FL"));
//        final LocalizedString TAXA_CANDIDATURA_name = new LocalizedString(pt(), "Taxa de Candidatura").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "TAXA_CANDIDATURA", TAXA_CANDIDATURA_name, defaultUnitOfMeasure(), true,
//                defaultVatType(), fromAcronymsToFinantialInstitutionList("FF"));
//        final LocalizedString RENOVACAO_INSCRICAO_name = new LocalizedString(pt(), "Renovação de Inscrição").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "RENOVACAO_INSCRICAO", RENOVACAO_INSCRICAO_name, defaultUnitOfMeasure(), true,
//                defaultVatType(), fromAcronymsToFinantialInstitutionList("FF"));
//        final LocalizedString CERTIDAO_FREQUENCIA_name = new LocalizedString(pt(), "Certidão de Frequência").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CERTIDAO_FREQUENCIA", CERTIDAO_FREQUENCIA_name, defaultUnitOfMeasure(), true,
//                defaultVatType(), fromAcronymsToFinantialInstitutionList("FF"));
//        final LocalizedString CERTIDAO_APROVEITAMENTO_ESCOLAR_TRANSICAO_ANO_name =
//                new LocalizedString(pt(), "Certidão de Aproveitamento Escolar / Transição de Ano").with(en(), "");
//        Product.create(EMOLUMENT_productGroup, "CERTIDAO_APROVEITAMENTO_ESCOLAR_TRANSICAO_ANO",
//                CERTIDAO_APROVEITAMENTO_ESCOLAR_TRANSICAO_ANO_name, defaultUnitOfMeasure(), true, defaultVatType(),
//                fromAcronymsToFinantialInstitutionList("FF"));
//    }
//}
