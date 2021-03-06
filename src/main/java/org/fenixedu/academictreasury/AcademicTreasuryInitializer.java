package org.fenixedu.academictreasury;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.fenixedu.academic.domain.treasury.TreasuryBridgeAPIFactory;
import org.fenixedu.academictreasury.domain.listeners.DebitEntryDeletionListener;
import org.fenixedu.academictreasury.domain.treasury.AcademicTreasuryBridgeImpl;
import org.fenixedu.academictreasury.services.accesscontrol.spi.AcademicTreasuryAccessControlExtension;
import org.fenixedu.treasury.services.accesscontrol.TreasuryAccessControlAPI;

@WebListener
public class AcademicTreasuryInitializer implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        final AcademicTreasuryBridgeImpl impl = new AcademicTreasuryBridgeImpl();
        impl.registerNewAcademicServiceRequestSituationHandler();
        impl.registerStandaloneEnrolmentHandler();
        impl.registerExtracurricularEnrolmentHandler();
        impl.registerImprovementEnrolmentHandler();
        
        TreasuryBridgeAPIFactory.registerImplementation(impl);
        
        TreasuryAccessControlAPI.registerExtension(new AcademicTreasuryAccessControlExtension());
        
        DebitEntryDeletionListener.attach();
        
        AcademicTreasuryBootstrap.process();
    }
}
