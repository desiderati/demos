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
import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {NgForm} from '@angular/forms';
import {HttpErrorResponse} from '@angular/common/http';

import {Quotation} from '../quotation';
import {QuotationService} from '../quotation.service';
import {InsuranceTypeService} from '../insurance-type.service';

import * as $ from 'jquery';
import {InsuranceType} from "../insurance-type";

@Component({
    selector: 'app-quotation-registration',
    templateUrl: 'quotation-registration.component.html',
    styleUrls: ['./quotation-registration.component.scss'],

})
export class QuotationRegistrationComponent implements OnInit {

    quotation = new Quotation(null, null, null, null);

    insuranceTypes: InsuranceType[];

    @ViewChild('quotationRegistrationForm', {static: false}) quotationRegistrationForm: NgForm;

    @Output() quotationCreatedUpdatedDeletedEvent = new EventEmitter();

    constructor(private quotationService: QuotationService, private insuranceTypeService: InsuranceTypeService) {
    }

    static clearErrorMessages() {
        $('#errorsContainer').hide();
        $('#errors').html('');
    }

    static addErrorMessage(message: string) {
        $('#errorsContainer').show();
        $('<li/>').html(message).appendTo('#errors');
    }

    static addValidationErrorMessages(messages: string[]) {
        $('#errorsContainer').show();
        for (let i = 0; i < messages.length; i++) {
            const input = messages[i].split(':')[0];
            const message = messages[i].split(':')[1];
            $('<li/>')
                .wrapInner(
                    $('<a/>')
                        .attr('href', 'javascript: void(0);')
                        .html(message)
                        .on('click', function () {
                            // Focus on the invalid field
                            $('input[name=\'' + input + '\']').focus();
                        })
                )
                .appendTo('#errors');
        }
    }

    ngOnInit(): void {
        QuotationRegistrationComponent.clearErrorMessages();
        this.fetchAllInsuranceTypes();
    }

    fetchAllInsuranceTypes() {
        this.insuranceTypeService.fetchAllInsuranceTypes().subscribe(
            (response: InsuranceType[]) => {
                this.insuranceTypes = response;
            });
    }

    createQuotation(quotation: Quotation) {
        this.quotationService.createQuotation(quotation).subscribe(
            () => {
                this.reset();
                this.quotationCreatedUpdatedDeletedEvent.emit();
            },
            (responseError: HttpErrorResponse) => {
                if (typeof (responseError.error.validationMessages) !== 'undefined') {
                    QuotationRegistrationComponent.addValidationErrorMessages(responseError.error.validationMessages);
                } else {
                    QuotationRegistrationComponent.addErrorMessage(responseError.error.message);
                }
            });
    }

    configureQuotation(quotation: Quotation) {
        quotation.insuranceType =
            this.insuranceTypes.find(insuranceType => insuranceType.id == quotation.insuranceType.id);
        this.quotation = quotation;
    }

    updateQuotation(quotation: Quotation, id: number) {
        this.quotationService.updateQuotation(quotation, id).subscribe(
            () => {
                this.reset();
                this.quotationCreatedUpdatedDeletedEvent.emit();
            },
            (responseError: HttpErrorResponse) => {
                if (typeof (responseError.error.validationMessages) !== 'undefined') {
                    QuotationRegistrationComponent.addValidationErrorMessages(responseError.error.validationMessages);
                } else {
                    QuotationRegistrationComponent.addErrorMessage(responseError.error.message);
                }
            });
    }

    deleteQuotation(quotation: Quotation) {
        this.quotationService.deleteQuotation(quotation.id).subscribe(
            () => {
                if (this.quotation.id === quotation.id) {
                    this.reset();
                }
                this.quotationCreatedUpdatedDeletedEvent.emit();
            },
            (responseError: HttpErrorResponse) => {
                this.reset();
                QuotationRegistrationComponent.addErrorMessage(responseError.error.message);
            });
    }

    calculateQuotation() {
        QuotationRegistrationComponent.clearErrorMessages();
        if (!!this.quotation.insuranceType && this.quotation.price > 0) {
            if (this.quotation.price < this.quotation.insuranceType.minCoverage) {
                this.quotation.value = null;
                QuotationRegistrationComponent.addErrorMessage("Value must be higher than minimum coverage!");

            } else {
                let price = this.quotation.price;
                if (price > this.quotation.insuranceType.maxCoverage) {
                    price = this.quotation.insuranceType.maxCoverage;
                }

                this.quotation.value =
                    price * (this.quotation.insuranceType.risk / 100);
            }
        } else {
            this.quotation.value = null;
        }
    }

    submit() {
        QuotationRegistrationComponent.clearErrorMessages();
        if (this.quotation.id === null) {
            console.log('Saving new quotation:', this.quotation);
            this.createQuotation(this.quotation);
        } else {
            console.log('Updating quotation:', this.quotation);
            this.updateQuotation(this.quotation, this.quotation.id);
        }
    }

    reset() {
        this.quotation = new Quotation(null, null, null, null);
        QuotationRegistrationComponent.clearErrorMessages();
        this.quotationRegistrationForm.reset();
    }
}
