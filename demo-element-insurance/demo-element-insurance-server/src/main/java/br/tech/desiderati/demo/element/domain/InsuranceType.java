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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Represents a type of insurance product in the system.
 * <p>
 * This entity stores information about different insurance products offered,
 * including their coverage ranges and risk levels.
 * Each insurance type can be associated with multiple quotations.
 *
 * @see Quotation
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Entity
public class InsuranceType implements Identity<Long> {

    /**
     * Unique identifier for the insurance type.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the insurance product.
     * This field cannot be blank.
     */
    @Column(nullable = false)
    @NotBlank(message = "{insuranceType.product.notBlank}")
    private String product;

    /**
     * The minimum coverage amount for this insurance type.
     * Must be a non-negative value.
     */
    @Column(nullable = false)
    @Min(value = 0, message = "{insuranceType.minCoverage.minValue}")
    @NotNull(message = "{insuranceType.minCoverage.notNull}")
    private Double minCoverage;

    /**
     * The maximum coverage amount for this insurance type.
     * Must be a non-negative value and should be greater than or equal to minCoverage.
     */
    @Column(nullable = false)
    @Min(value = 0, message = "{insuranceType.maxCoverage.minValue}")
    @NotNull(message = "{insuranceType.maxCoverage.notNull}")
    private Double maxCoverage;

    /**
     * The risk percentage associated with this insurance type.
     * Must be between 0 and 100 (inclusive).
     */
    @Column(nullable = false)
    @Min(value = 0, message = "{insuranceType.risk.minValue}")
    @Max(value = 100, message = "{insuranceType.risk.maxValue}")
    @NotNull(message = "{insuranceType.risk.notNull}")
    private Integer risk;

}
