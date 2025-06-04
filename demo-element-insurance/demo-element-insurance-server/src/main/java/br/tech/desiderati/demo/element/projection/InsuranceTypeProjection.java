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
package br.tech.desiderati.demo.element.projection;

import br.tech.desiderati.demo.element.domain.InsuranceType;
import br.tech.desiderati.demo.element.domain.Quotation;
import org.springframework.data.rest.core.config.Projection;

/**
 * A Spring Data REST projection for {@link Quotation} entities that includes the associated {@link InsuranceType}.
 * <p>
 * This projection is used by the QuotationRepository to include the complete InsuranceType entity
 * in the REST API responses when retrieving quotations.
 * It defines which properties of the Quotation entity should be included in the response,
 * along with the associated InsuranceType.
 * <p>
 * The projection is named "withInsuranceType" and can be requested explicitly in API calls
 * using the projection parameter, e.g., /api/quotations?projection=withInsuranceType.
 * It is also used as the default excerpt projection for the QuotationRepository.
 *
 * @see Quotation
 * @see InsuranceType
 * @see br.tech.desiderati.demo.element.repository.QuotationRepository
 * @see org.springframework.data.rest.core.config.Projection
 */
@SuppressWarnings("unused")
@Projection(name = "withInsuranceType", types = {Quotation.class})
public interface InsuranceTypeProjection {

    /**
     * Gets the ID of the quotation.
     *
     * @return the quotation ID
     */
    Long getId();

    /**
     * Gets the price of the quotation.
     *
     * @return the quotation price
     */
    Double getPrice();

    /**
     * Gets the value (coverage amount) of the quotation.
     *
     * @return the quotation value
     */
    Double getValue();

    /**
     * Gets the insurance type associated with the quotation.
     * This method returns the complete InsuranceType entity, not just its ID.
     *
     * @return the associated insurance type
     */
    InsuranceType getInsuranceType();

}
