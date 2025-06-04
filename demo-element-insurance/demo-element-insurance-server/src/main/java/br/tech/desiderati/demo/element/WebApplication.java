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
package br.tech.desiderati.demo.element;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Main application class for the Element Insurance server.
 * <p>
 * This class serves as the entry point for the Spring Boot application.
 * It is annotated with {@link SpringBootApplication}, which enables auto-configuration,
 * component scanning, and property support.
 * The application provides a REST API for managing insurance types and quotations using Spring Data REST.
 * <p>
 * The application is configured to run on port 9090 with the context path "/element-insurance"
 * (as defined in application properties).
 * It connects to a PostgreSQL database for data persistence.
 * <p>
 * Key features of the application include:
 * <ul>
 *   <li>RESTful API for insurance types and quotations</li>
 *   <li>Automatic CRUD operations using Spring Data REST</li>
 *   <li>HAL Explorer for API documentation and testing</li>
 *   <li>Validation for entity properties</li>
 *   <li>Cross-origin resource sharing (CORS) support</li>
 * </ul>
 *
 * @see br.tech.desiderati.demo.element.domain.InsuranceType
 * @see br.tech.desiderati.demo.element.domain.Quotation
 * @see br.tech.desiderati.demo.element.repository.InsuranceTypeRepository
 * @see br.tech.desiderati.demo.element.repository.QuotationRepository
 */
@SpringBootApplication
public class WebApplication {

    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args command line arguments passed to the application
     */
    public static void main(String[] args) {
        new SpringApplicationBuilder(WebApplication.class).run(args);
    }
}
