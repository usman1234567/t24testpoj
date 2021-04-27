package com.training;

import com.temenos.api.TField;
import com.temenos.api.TStructure;
import com.temenos.api.TValidationResponse;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.account.AccountRecord;
import com.temenos.t24.api.records.eberror.EbErrorRecord;
import com.temenos.t24.api.records.fundstransfer.FundsTransferRecord;
import com.temenos.t24.api.system.DataAccess;

/**
 * TODO: Document me!
 *
 * @author Administrator
 *
 */
public class VersionRoutinesDemo extends RecordLifecycle {

    @Override
    public TValidationResponse validateRecord(String application, String currentRecordId, TStructure currentRecord,
            TStructure unauthorisedRecord, TStructure liveRecord, TransactionContext transactionContext) {
        

        FundsTransferRecord ft = new FundsTransferRecord(currentRecord);
        
        DataAccess da = new DataAccess(this);
        
        String creditAcct  = ft.getCreditAcctNo().getValue();
        String cat = "";
        try{
            AccountRecord acRec = new AccountRecord(da.getRecord("ACCOUNT", creditAcct));
            cat = acRec.getCategory().getValue();
        }
        catch(Exception ex)
        {
            throw new Error("Account does not exist");
        }
        
        
        TField dbtAmt =  ft.getDebitAmount();
        TField dbtCurr = ft.getDebitCurrency();
        TField crdtCurr = ft.getCreditCurrency();
        
        String dbtVal = dbtAmt.getValue();
        String crtVal = crdtCurr.getValue();
        String value = dbtAmt.getValue();
        
        double test = 0;
        if(!dbtVal.equals(""))
            test = Double.parseDouble(dbtVal);
                
        if(!dbtVal.equals(crtVal))
        {
            crdtCurr.setError(String.valueOf(test));
            crdtCurr.setOverride("TEST");
        }
        if(cat.equals("1001"))
        {
            ft.getCreditAcctNo().setError("You can not perform any trx using this category");
        }
        
        
        return ft.getValidationResponse();
    }

}
