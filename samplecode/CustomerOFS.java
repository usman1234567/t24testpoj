package com.training;

import java.util.List;

import com.temenos.api.TBoolean;
import com.temenos.api.TStructure;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.account.AccountRecord;
import com.temenos.t24.api.records.customer.CustomerRecord;
import com.temenos.t24.api.records.sector.SectorRecord;

/**
 * TODO: Document me!
 *
 * @author Administrator
 *
 */
public class CustomerOFS extends RecordLifecycle {

    @Override
    public void updateCoreRecord(String application, String currentRecordId, TStructure currentRecord,
            TStructure unauthorisedRecord, TStructure liveRecord, List<String> versionNames, TBoolean isZeroAuth,
            List<String> currentRecordIds, List<TStructure> currentRecords, TransactionContext transactionContext) {
        // TODO Auto-generated method stub
        
        CustomerRecord cusRec = new CustomerRecord(currentRecord);
        
        AccountRecord acRec = new AccountRecord(this);
        acRec.setCustomer(currentRecordId);
        acRec.setAccountOfficer("1");
        acRec.setCategory("1001");
        acRec.setCurrency("USD");
        
        
        versionNames.add("ACCOUNT,");
        currentRecordIds.add("");
        currentRecords.add(acRec.toStructure());
        
        SectorRecord secRec = new SectorRecord(this);
        secRec.setDescription("test", 0);
        secRec.setShortName("corporate", 0);
        
        versionNames.add("SECTOR,TEST");
        currentRecordIds.add("");
        currentRecords.add(secRec.toStructure());
        
        
        
    }

}
