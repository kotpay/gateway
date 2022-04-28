package com.github.kotpay.gateway.entity

import org.hibernate.Hibernate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "order_info")
open class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Column(name = "order_id", nullable = false, unique = true, length = 36)
    open var orderId: String? = null

    @Column(name = "amount", nullable = false)
    open var amount: Long? = null

    @Column(name = "currency_code", nullable = false, length = 3)
    open var currencyCode: String? = null

    @Column(name = "return_url", nullable = false)
    open var returnUrl: String? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    open var status: Status? = null

    enum class Status {
        CREATED,
        DEPOSITED,
        DECLINED,
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Order

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , orderId = $orderId , amount = $amount , currencyCode = $currencyCode , returnUrl = $returnUrl , status = $status )"
    }
}