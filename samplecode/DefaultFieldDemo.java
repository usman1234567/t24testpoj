package com.training;

import com.temenos.api.TStructure;
import com.temenos.t24.api.complex.eb.templatehook.InputValue;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.customer.CustomerRecord;

/**
 * TODO: Document me!
 *
 * @author Administrator
 *
 */
public class DefaultFieldDemo extends RecordLifecycle {

    @Override
    public void defaultFieldValuesOnHotField(String application, String currentRecordId, TStructure currentRecord,
            InputValue currentInputValue, TStructure unauthorisedRecord, TStructure liveRecord,
            TransactionContext transactionContext) {
        
        String fieldName = currentInputValue.getFieldName();
        CustomerRecord cusRec = new CustomerRecord(currentRecord);
        
        if(fieldName.equals("MNEMONIC"))
        {
            cusRec.setShortName("TABANI", 0);
            cusRec.setName1("Noushad", 0);
            cusRec.setSector("1000");
            cusRec.setLanguage("1");
            cusRec.getLocalRefField("CERT.DOMICILE").setValue("KSA");
        }
        if(fieldName.equals("CATEGORY"))
        {
            
        }
        
        currentRecord.set(cusRec.toStructure());
    }

}
