package com.trilogyed.adminapi.model;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

/*
	invoice_id int(11) not null auto_increment primary key,
    customer_id int(11) not null,
    purchase_date date not null
 */
public class Invoice {

    @NotNull
    private int invoiceId;

    @NotNull
    private int customerId;

    @NotNull
    private LocalDate purchaseDate;

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return invoiceId == invoice.invoiceId &&
                customerId == invoice.customerId &&
                Objects.equals(purchaseDate, invoice.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, customerId, purchaseDate);
    }
}
