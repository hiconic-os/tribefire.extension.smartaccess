// ============================================================================
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ============================================================================
// ============================================================================
// Copyright BRAINTRIBE TECHNOLOGY GMBH, Austria, 2002-2022
// 
// This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
// 
// This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public License along with this library; See http://www.gnu.org/licenses/.
// ============================================================================
package com.braintribe.model.processing.query.smart.test.builder;

import java.util.Arrays;

import com.braintribe.model.processing.query.smart.test.builder.repo.RepositoryDriver;
import com.braintribe.model.processing.query.smart.test.model.accessB.PersonB;

/**
 * 
 */
public class PersonBBuilder extends AbstractBuilder<PersonB, PersonBBuilder> {

	public static PersonBBuilder newInstance(SmartDataBuilder dataBuilder) {
		return new PersonBBuilder(dataBuilder.repoDriver());
	}

	public PersonBBuilder(RepositoryDriver repoDriver) {
		super(PersonB.class, repoDriver);
	}

	public PersonBBuilder nameB(String value) {
		instance.setNameB(value);
		return self;
	}

	public PersonBBuilder parentA(String value) {
		instance.setParentA(value);
		return self;
	}

	public PersonBBuilder companyNameB(String value) {
		instance.setCompanyNameB(value);
		return self;
	}

	public PersonBBuilder ageB(int value) {
		instance.setAgeB(value);
		return this;
	}

	public PersonBBuilder birthDate(String value) {
		instance.setBirthDate(value);
		return this;
	}

	public PersonBBuilder dates(String... values) {
		instance.setDates(Arrays.asList(values));
		return this;
	}

}
