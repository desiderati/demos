/*
 * Copyright (c) 2025 - Felipe Desiderati
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package br.tech.desiderati.demo.element.domain;

import dev.springbloom.data.jpa.Identity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Represents an insurance quotation in the system.
 * <p>
 * This entity stores information about quotations created for specific insurance types.
 * Each quotation is associated with exactly one insurance type and includes pricing
 * and value information.
 *
 * @see InsuranceType
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"insuranceType"})
@Entity
public class Quotation implements Identity<Long> {

    /**
     * Unique identifier for the quotation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The price of the quotation.
     * Must be at least 1.
     */
    @Column(nullable = false)
    @Min(value = 1, message = "{quotation.price.minValue}")
    @NotNull(message = "{quotation.price.notNull}")
    private Double price;

    /**
     * The value of the quotation (coverage amount).
     * Must be a non-negative value.
     */
    @Column(nullable = false)
    @Min(value = 0, message = "{quotation.value.minValue}")
    @NotNull(message = "{quotation.value.notNull}")
    private Double value;

    /**
     * The insurance type associated with this quotation.
     * Each quotation must be associated with exactly one insurance type.
     */
    @ManyToOne(optional = false)
    @NotNull(message = "{quotation.insuranceType.notNull}")
    private InsuranceType insuranceType;

}
