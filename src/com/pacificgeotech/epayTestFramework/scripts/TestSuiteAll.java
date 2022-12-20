package com.pacificgeotech.epayTestFramework.scripts;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

		CLM1A001.class, CLM1A002.class, CLM1A003.class, CLM1A004.class, CLM1A005.class, CLM1A006.class, CLM1A007.class,

		CLM1B001.class, CLM1B002.class, CLM1B003.class, CLM1B004.class, CLM1B005.class, CLM1B006.class, CLM1B007.class,

		CLM1C003.class, AWR11001.class, AWR2A004.class, AWR2A005.class, AWR2A006.class, AWR2A007.class, AWR2A013.class,
		AWRRC001.class, AWRRC003.class, AWRER001.class, AWRER003.class, AWR3A001.class, AWR3A002.class, AWR12A001.class,
		AWR12A002.class, CLM1D001.class, CLM1D002.class, CLM1D003.class, CLM1D004.class, AWRRC006.class, AWRER006.class,
		AWR2A008.class, AWR3A004.class, AWR3A007.class, AWR3A010.class, AWR2A002.class,

		RAL.class, ARAL.class, REI.class, AREI.class, SWO.class, RSWO.class, ASWO.class, INSP1B002.class, 
		SRET.class, ASRET.class, ASRETYears.class, MRTReserve.class, MRTReserveClaim.class, 
		MRTReserveWithdraw.class, REncumbrance.class, RSurenderMLand.class, 
		RTransferML.class, ManagePenaltyInterestDates.class, MPenaltyIRate.class, RRWMLTaxOrInterest.class, ManageMiningLandTenureStatus.class, RecordMiningLandTaxExemption.class, 
		AddBulletinBoard.class, ClaimDatabaseAmendment.class, CreateAccountandSubAccount.class, ProduceandSendDocumentationtoSubmitter.class, ReviewPermitSubmission.class, 
		ConfirmSRObyClient.class,
		
		CLM2A001.class, CLM2A002.class, CLM2A003.class, CLM2A004.class, CLM2A005.class, CLM2A006.class, CLM2A008.class,
		CLM2A009.class, CLM2A013.class, CLM2A014.class, CLM2A015.class,

		CLM2B001.class, CLM2B002.class, CLM2B003.class, CLM2B004.class, CLM2B005.class, 

		CLM2C001.class, CLM2C002.class, CLM2C003.class, CLM2C005.class, CLM2C006.class, CLM2C007.class, CLM2C011.class,
		CLM2C012.class,

		CLM2D001.class, CLM1D005.class, CLM2C008.class, CLM2C009.class,

		CLM3001.class, CLM3002.class, CLM3004.class, CLM3005.class, CLM3006.class, CLM3007.class, CLM3008.class,
		CLM3009.class, CLM3010.class, CLM3011.class, CLM3013.class, CLM3014.class, CLM3015.class,

		CLM6001.class, CLM6002.class, // CLM6003.class,
		CLM6004.class, CLM6005.class, CLM6006.class, CLM6007.class,

		AWR2A001.class, AWR2A002.class,
		SetImpendingTermination.class, PaymentInPlace.class,

		// CM10001.class,
		CM10002.class,
		// CM10003.class,
		CM10004.class,

		// CM10A001.class,
		// CM10A002.class,

		CM10B001.class, CM10B002.class,
		// CM11001.class,
		// CM11002.class,
		// CM1A001.class,
		// CM1A002.class,
		// CM1A003.class,
		// CM1A004.class,
		// CM1A006.class,
		// CM1B001.class,
		// CM1B002.class,
		// CM1B003.class,
		// CM1B004.class,
		// CM1B005.class,
		// CM1B008.class,
		CM11001.class, CM11002.class, CM2001.class, CM2002.class,
		// CM2003.class,
		// CM2004.class,
		// CM2005.class,
		CM3001.class, CM3002.class,
		// CM3003.class,
		// CM3004.class,
		CM3005.class,
		// CM3006.class,
		// CM3007.class,
		CM3008.class, CM3009.class, CM3011.class,

		CM4001.class, CM4002.class, CM4003.class, CM4004.class, CM4005.class, CM4006.class, CM4007.class, CM4008.class,
		CM4009.class,

		CM5001.class, CM5002.class, CM5003.class, CM5004.class, CM5005.class, CM5006.class, CM5007.class,

		CM6001.class, CM6002.class, CM6003.class, CM6004.class, CM6005.class, CM6006.class,

		CM7001.class, CM7002.class, CM7003.class, CM7004.class, CM7005.class, CM7009.class,

		CM8001.class, CM8005.class, CM8006.class, CM8007.class, CM8008.class,

		CM9001.class, CM9002.class, CM9003.class, CM9004.class, CM9005.class, CM9006.class, CM9007.class,

		CM10001.class, CM10002.class, CM10003.class, CM10004.class,

		CM10A001.class, CM10A002.class,

		CM10B001.class, CM10B002.class,

		EXP1001.class, EXPEC006.class, EXPVS001.class, EXP1003.class, EXP1004.class, EXP1010.class, EXP2A001.class,
		EXP2A002.class, EXP2A003.class, EXP2A004.class, EXP2A005.class, EXP2A006.class, EXP2A007.class, EXPVS001.class,
		EXPVS002.class, EXPVS003.class, EXPDV001.class, EXPDV002.class, EXPEC001.class, EXPEC002.class, EXPEC007.class,
		Permit.class, PermitValidate.class, Permit3rdPartyValidation.class, AddAmenPermit.class, PermitPreparePackage.class,
		PermitContactThirdParties.class, CheckPlanSubmissionElevationCandidate.class, PlanAddAmendremoveListItemsConfirmList.class,
		PlanDo3rdPartyVerifications.class, PlanPrepareReferralPackageDeliverListParties.class,

		F1001.class, F1002.class, F2001.class, F2002.class, F2005.class, F2006.class, F8001.class, F8002.class,
		F8003.class, F8004.class,

		LGN1001.class, LGN1002.class,
		/*
		 * LGN1003.class, LGN1004.class, LGN1005.class, LGN1006.class,
		 * LGN1007.class, LGN1008.class, LGN1009.class, LGN2001.class,
		 */
		MP1001.class, MP1002.class, MP1003.class, MP1004.class, MP1005.class, MP1006.class, MP1007.class, MP1008.class,
		MP1009.class, MP1010.class, MP1011.class, MP1012.class, MP1013.class,

		MP1001SRO.class,
		// MP1018.class,
		MP1B001.class, MP1B002.class, MP1B003.class, MP1B004.class, MP1B005.class, MP1B006.class, MP1B007.class,
		MP1B009.class, MP1B010.class, MP1B011.class,
		// MP1B012.class,
		// MP1B013.class,
		MP1B017.class, MP1B018.class, MP1B019.class, MP1B020.class, MP1B021.class,

		MP2001.class, MP2002.class, MP2003.class, MP2004.class, MP2005.class, MP2006.class,
		// MP2007.class,
		MP2B001.class, MP2B002.class, MP2B003.class, MP2B004.class, MP2B005.class,

		MP4001.class, MP4002.class, MP4003.class,
		// MP4B001.class,

		MP5001.class, MP5002.class, MP5003.class, MP5006.class,

		GIS1002.class,

		PL1001.class, PL1002.class, PL1003.class, PL1004.class, PL1005.class, PL1006.class, PL1007.class, PL1008.class,

		PL2001.class, PL2002.class, PL2003.class, PL2004.class, PL2005.class, PL2006.class, PL2007.class, PL2008.class,

		PL3001.class, PL3002.class, PL3003.class, PL3004.class, PL3005.class, PL3006.class, PL3007.class, PL3008.class,

		PL4001.class, PL4002.class, PL4003.class, PL4004.class, PL4005.class, PL4006.class, PL4007.class, PL4008.class,

		PL7001.class, PL7002.class, PL7003.class,
		
		STDISP11003.class, STDISP11002.class, STDISP25005.class, STDISP25003.class, DISP26.class, DISP4a1.class, DISP51.class, STDISP51005.class, DISP31.class, STDISP4a7007.class,
		RejectRenewalOfLease.class, DISP4b1.class, STDISP4b1002.class, STDISP51006.class, DISP56.class, DISP712345.class, DISP61234.class, STDISP75002.class, STDISP75003.class,
		DISP76.class, DISP96.class, DISP912345.class, DISP96SurrenderIncomplete.class, DISP10123456.class, STDISP1050012.class, DISP107.class, DISP11123456.class, DISP117.class,
		DISP12123456.class, DISP13123456.class, INSP1123456.class, INSP1700123456.class, 

		CM5008.class, CM5009.class, CM5010.class,

		CM6001.class, CM6002.class, CM6003.class, CM6004.class, CM6005.class, CM6006.class,

		CM7002.class, CM7003.class,

})
public class TestSuiteAll {
}
