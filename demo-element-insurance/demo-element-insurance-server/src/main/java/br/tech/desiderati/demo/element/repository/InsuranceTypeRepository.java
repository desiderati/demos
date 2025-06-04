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
package br.tech.desiderati.demo.element.repository;

import br.tech.desiderati.demo.element.domain.InsuranceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Repository interface for managing {@link InsuranceType} entities.
 * <p>
 * This repository provides CRUD operations for InsuranceType entities and
 * is automatically exposed as a REST resource at the path "/insurancetypes".
 * The @CrossOrigin annotation enables cross-origin requests, allowing the
 * frontend application to access this API.
 * </p>
 * Spring Data JPA automatically implements this interface at runtime, providing
 * standard methods like findAll(), findById(), save(), delete(), etc.
 *
 * @see InsuranceType
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see org.springframework.data.rest.core.annotation.RepositoryRestResource
 */
@CrossOrigin
@RepositoryRestResource(path = "insurancetypes")
public interface InsuranceTypeRepository extends JpaRepository<InsuranceType, Long> {

}
