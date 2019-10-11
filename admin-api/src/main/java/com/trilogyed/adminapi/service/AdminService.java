package com.trilogyed.adminapi.service;

import com.trilogyed.adminapi.exception.ImpossibleDeleteException;
import com.trilogyed.adminapi.exception.NullListReturnException;
import com.trilogyed.adminapi.exception.NullObjectReturnException;
import com.trilogyed.adminapi.invoiceViewmodel.InvoiceViewModel;
import com.trilogyed.adminapi.model.*;
import com.trilogyed.adminapi.util.feign.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminService {

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private InventoryClient inventoryClient;

    @Autowired
    private InvoiceClient invoiceClient;

    @Autowired
    private LevelUpClient levelUpClient;

    @Autowired
    private ProductClient productClient;

    public AdminService(){}

    public AdminService(CustomerClient customerClient, InventoryClient inventoryClient,
                        InvoiceClient invoiceClient, LevelUpClient levelUpClient, ProductClient productClient){

        this.customerClient = customerClient;
        this.inventoryClient = inventoryClient;
        this.invoiceClient = invoiceClient;
        this.levelUpClient = levelUpClient;
        this.productClient = productClient;
    }

    //CRUD for Customer
    public Customer createCustomer(Customer customer){return customerClient.createCustomer(customer);}

    public Customer getCustomer(int customerId){
        Customer custFromService = customerClient.getCustomer(customerId);
        if(custFromService==null)
            throw new NullObjectReturnException("There is no customer given the id: "+customerId);
        return custFromService;
    }

    public List<Customer> getAllCustomers(){
        List<Customer>customersFromService = customerClient.getAllCustomers();

        if(customersFromService.size()==0)
            throw new NullListReturnException("There are no customers in our database");


        return customersFromService;
    }

    public void updateCustomer(Customer customer){customerClient.updateCustomer(customer);}

    //Business Logic Check level up...we need to delete it
    //Check Invoice and then delete....if delete we then checkInvoiceItem then Inventory then product WOW
    public void deleteCustomer(int customerId){
        LevelUp levelUpCheck = levelUpClient.getLevelUpByCustomer(customerId);
        List<InvoiceViewModel> invoiceCheckList = invoiceClient.getAllInvoicesByCustomer(customerId);

        if(invoiceCheckList.size()==0) {
            customerClient.deleteCustomer(customerId);
            //  Invoice invoiceCheck = invoiceClient.g
            if (levelUpCheck == null) {
//            levelUpClient.deleteLevelUp(levelUpCheck.getLevelUpId());
                customerClient.deleteCustomer(customerId);
            }
            else{
                throw new ImpossibleDeleteException("We cannot perform this delete as the customer has levelUp pounts, which we are keeping track of");
            }
        }
        else{
            throw new ImpossibleDeleteException("We cannot delete this customer as there is an existing InvoiceViewModel associated with this customer");
        }


        //logic for invoice check
    }


    //CRUD for Inventory
    public Inventory createInventory(Inventory inventory){return  inventoryClient.createInventory(inventory);}

    public Inventory getInventory(int inventoryId){return inventoryClient.getInventory(inventoryId);}

    public List<Inventory> getAllInventory(){return inventoryClient.getAllInventory();}

    public void updateInventory(Inventory inventory){inventoryClient.updateInventory(inventory);}

    public void deleteInventory(int inventoryId){inventoryClient.deleteInventory(inventoryId);}


    //CRUD for Invoice
    public InvoiceViewModel createInvoice(Invoice invoice){return invoiceClient.createInvoice(invoice);}

    public InvoiceViewModel getInvoice(int invoiceId){return invoiceClient.getInvoice(invoiceId);}

    public List<InvoiceViewModel> getAllInvoices(){return invoiceClient.getAllInvoices();}

    public List<InvoiceViewModel> getAllInvoicesByCustomer(int customerId){return invoiceClient.getAllInvoicesByCustomer(customerId);}

    public void updateInvoice(Invoice invoice){invoiceClient.updateInvoice(invoice);}

    public void deleteInvoice (int invoiceId){invoiceClient.deleteInvoice(invoiceId);}

    //----------------------------------------------------------\
    //Crud for InvoiceItem from same feign invoiceClient
    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem){
        return invoiceClient.createInvoiceItem(invoiceItem);
    }

    public InvoiceItem getInvoiceItem(int invoiceItemId){return invoiceClient.getInvoiceItem(invoiceItemId);}

    public List<InvoiceItem> getAllInvoiceItems(){return invoiceClient.getAllInvoiceItems();}

    public void updateInvoiceItem(InvoiceItem invoiceItem){invoiceClient.updateInvoiceItem(invoiceItem);}

    public void deleteInvoiceItem(int invoiceItemId){invoiceClient.deleteInvoiceItem(invoiceItemId);}


    //CRUD LevelUp
    public LevelUp createLevelUp(LevelUp levelUp){return levelUpClient.createLevelUp(levelUp);}

    public LevelUp getLevelUp(int levelUpId){return levelUpClient.getLevelUp(levelUpId);}

    public List<LevelUp> getLevelUps(){return levelUpClient.getLevelUps();}

    public void updateLevelUp(LevelUp levelUp){levelUpClient.updateLevelUp(levelUp);}

    public void deleteLevelUp(int levelUpId){levelUpClient.deleteLevelUp(levelUpId);}


    //CRUD Product
    public Product createProduct(Product product){return productClient.createProduct(product);}

    public Product getProduct(int productId){return productClient.getProduct(productId);}

    public List<Product> getAllProducts(){return productClient.getAllProducts();}

    public void updateProduct(Product product){productClient.updateProduct(product);}

    public void deleteProduct(int productId){productClient.deleteProduct(productId);}
    //When we are deleting a product, first check inventory with feign call, ensure that it is null then delete
    //if it is not null, check quantity , and make sure each inventory quantity is 0 for that productId
    //if the inventory quantity is 0, then check none of them have invoice-items with invoice-item

    //WE DO NOT HAVE A METHOD to get all Inventory by product id so we have to pull all inventory and then
    //filter by a productId and genearate a new list

    //then we stream through to check that the quantity is zero



}
