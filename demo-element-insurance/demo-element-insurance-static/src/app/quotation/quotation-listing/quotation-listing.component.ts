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
import {Component, EventEmitter, OnInit, Output} from '@angular/core';

import {Quotation} from '../quotation';
import {QuotationService} from '../quotation.service';

@Component({
    selector: 'app-quotation-listing',
    templateUrl: 'quotation-listing.component.html',
    styleUrls: ['./quotation-listing.component.scss']
})
export class QuotationListingComponent implements OnInit {

    @Output() quotationUpdateEvent = new EventEmitter<Quotation>();
    @Output() quotationRemoveEvent = new EventEmitter<Quotation>();

    quotations: Quotation[];

    constructor(private quotationService: QuotationService) {
    }

    ngOnInit(): void {
        this.fetchAllQuotations();
    }

    fetchAllQuotations() {
        this.quotationService.fetchAllQuotations().subscribe(
            (response: Quotation[]) => {
                this.quotations = response;
            });
    }

    update(id: number) {
        this.selectQuotation(id, (quotation: Quotation) => {
            this.quotationUpdateEvent.emit(quotation);
            console.log('Quotation to be updated:', quotation);
        });
    }

    remove(id: number) {
        this.selectQuotation(id, (quotation: Quotation) => {
            this.quotationRemoveEvent.emit(quotation);
            console.log('Quotation to be deleted:', quotation);
        });
    }

    private selectQuotation(id: number, callback: any) {
        for (let i = 0; i < this.quotations.length; i++) {
            if (this.quotations[i].id === id) {
                const innerQuotation = Object.assign({}, this.quotations[i]);
                callback(innerQuotation);
                break;
            }
        }
    }
}
