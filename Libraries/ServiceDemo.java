package com.training;

import java.util.List;

import com.temenos.api.TBoolean;
import com.temenos.api.TStructure;
import com.temenos.t24.api.complex.eb.servicehook.ServiceData;
import com.temenos.t24.api.complex.eb.servicehook.SynchronousTransactionData;
import com.temenos.t24.api.complex.eb.servicehook.TransactionControl;
import com.temenos.t24.api.hook.system.ServiceLifecycle;
import com.temenos.t24.api.records.customer.CustomerRecord;
import com.temenos.t24.api.system.DataAccess;
import com.temenos.t24.api.system.Session;

/**
 * TODO: Document me!
 *
 * @author Administrator
 *
 */
public class ServiceDemo extends ServiceLifecycle {

    @Override
    public List<String> getIds(ServiceData serviceData, List<String> controlList) {
        // TODO Auto-generated method stu
        
        DataAccess da = new DataAccess(this);
        List<String> retList = null;
        
        retList = da.selectRecords("BNK", "CUSTOMER", "", "WITH SECTOR EQ '1000'");
        
        return retList;
        
    }

    @Override
    public void updateRecord(String id, ServiceData serviceData, String controlItem,
            TransactionControl transactionControl, List<SynchronousTransactionData> transactionData,
            List<TStructure> records) {
        // TODO Auto-generated method stub
        super.updateRecord(id, serviceData, controlItem, transactionControl, transactionData, records);
    }

    @Override
    public void inputRecord(String id, ServiceData serviceData, String controlItem, TBoolean setZeroAuth,
            List<String> versionNames, List<String> recordIds, List<TStructure> records) {
        
        Session session = new Session(this);
        session.getCurrentVariable("!TODAY");
        DataAccess da = new DataAccess(this);
        CustomerRecord cusRec = new CustomerRecord(da.getRecord("CUSTOMER", id));
        
        cusRec.setShortName("TEST" , 0);
        
        versionNames.add("CUSTOMER,SERVICE.TEST");
        recordIds.add(id);
        records.add(cusRec.toStructure());
        setZeroAuth.set(true);
        
    }

}
