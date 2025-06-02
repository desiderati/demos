/*
 * Copyright (c) 2019 - Felipe Desiderati
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
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {map, catchError} from 'rxjs/operators';

import {handleError} from './exception-handler.function';
import {environment} from "../../environments/environment";

import {Quotation} from "./quotation";
import {Observable} from "rxjs";
import {InsuranceTypeService} from "./insurance-type.service";

const apiUrl = environment.apiUrl;

@Injectable({
    providedIn: 'root'
})
export class QuotationService {

    static apiUrl = apiUrl + 'quotations';

    constructor(private http: HttpClient) {
    }

    fetchAllQuotations(): Observable<Quotation[]> {
        return this.http.get<Quotation[]>(QuotationService.apiUrl).pipe(
            map((res: any) => res._embedded.quotations),
            catchError(handleError('QuotationService', 'fetching all quotations')));
    }

    createQuotation(quotation: Quotation) {
        // To add the @ManyToOne relation, Spring Data Rest requests that you send
        // the URL (HATEOAS) of the dependency!!!
        const innerQuotation = Object.assign({}, quotation);
        innerQuotation.insuranceType = InsuranceTypeService.apiUrl + '/' + innerQuotation.insuranceType.id;

        return this.http.post(QuotationService.apiUrl, innerQuotation).pipe(
            catchError(handleError('QuotationService', 'creating quotation')));
    }

    updateQuotation(quotation: Quotation, id: number) {
        // To add the @ManyToOne relation, Spring Data Rest requests that you send
        // the URL (HATEOAS) of the dependency!!!
        const innerQuotation = Object.assign({}, quotation);
        innerQuotation.insuranceType = InsuranceTypeService.apiUrl + '/' + innerQuotation.insuranceType.id;

        // We must use PATCH, otherwise Spring Data Rest will not update the relationship.
        return this.http.patch(QuotationService.apiUrl + '/' + id, innerQuotation).pipe(
            catchError(handleError('QuotationService', 'updating quotation')));
    }

    deleteQuotation(id: number) {
        return this.http.delete(QuotationService.apiUrl + '/' + id).pipe(
            catchError(handleError('QuotationService', 'deleting quotation')));
    }
}
