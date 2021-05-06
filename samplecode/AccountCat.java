package com.training;

import com.temenos.api.TStructure;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.account.AccountRecord;

/**
 * TODO: Document me!
 *
 * @author Administrator
 *
 */
public class AccountCat extends RecordLifecycle {

    @Override
    public String checkId(String currentRecordId, TransactionContext transactionContext) {
        // TODO Auto-generated method stub
        String newID = "Test" + currentRecordId ;
        return newID;
    }

    @Override
    public void defaultFieldValues(String application, String currentRecordId, TStructure currentRecord,
            TStructure unauthorisedRecord, TStructure liveRecord, TransactionContext transactionContext) {
        // TODO Auto-generated method stub
        
        AccountRecord acRec = new AccountRecord(currentRecord);
        
        String cat = acRec.getCategory().getValue();
        
        if(!cat.equals("1001"))
        {
            throw new Error("This version can only be used to edit the records with Category 1001 only");
        }
        
    }
}
