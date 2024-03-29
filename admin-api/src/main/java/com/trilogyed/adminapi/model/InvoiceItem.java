package com.trilogyed.adminapi.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceItem {

    @NotNull
    private int invoiceItemId;

    @NotNull
    private int invoiceId;

    @NotNull
    private int inventoryId;

    @NotNull
    @PositiveOrZero
    private int quantity;

    @NotNull
    private BigDecimal unitPrice;

    public int getInvoiceItemId() {
        return invoiceItemId;
    }

    public void setInvoiceItemId(int invoiceItemId) {
        this.invoiceItemId = invoiceItemId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItem that = (InvoiceItem) o;
        return invoiceItemId == that.invoiceItemId &&
                invoiceId == that.invoiceId &&
                inventoryId == that.inventoryId &&
                quantity == that.quantity &&
                Objects.equals(unitPrice, that.unitPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceItemId, invoiceId, inventoryId, quantity, unitPrice);
    }
}
