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
package com.braintribe.model.access.smart.test.query;

import org.junit.Test;

import com.braintribe.model.processing.query.smart.test.model.accessA.Id2UniqueEntityA;
import com.braintribe.model.processing.query.smart.test.model.smart.Id2UniqueEntity;
import com.braintribe.model.processing.query.smart.test.model.smart.SmartPersonA;
import com.braintribe.model.query.SelectQuery;

/**
 * See Id2Unique_PlannerTests
 */
public class Id2Unique_Tests extends AbstractSmartQueryTests {

	@Test
	public void propertyEntityCondition() {
		Id2UniqueEntityA u1 = bA.id2UniqueEntityA("u1").create();
		Id2UniqueEntityA u2 = bA.id2UniqueEntityA("u2").create();
		Id2UniqueEntityA u3 = bA.id2UniqueEntityA("u3").create();

		bA.personA("p1").id2UniqueEntityA(u1).create();
		bA.personA("p2").id2UniqueEntityA(u2).create();
		bA.personA("p3").id2UniqueEntityA(u3).create();

		Id2UniqueEntity su1 = id2UniqueEntity(u1);

		// @formatter:off
		SelectQuery selectQuery = query()		
				.from(SmartPersonA.class, "p")
				.select("p", "nameA")
				.where()
					.property("p", "id2UniqueEntityA").eq().entity(su1)
				.done();
		// @formatter:on

		evaluate(selectQuery);

		assertResultContains("p1");
		assertNoMoreResults();
	}

	@Test
	public void collectionEntityCondition() {
		Id2UniqueEntityA u1 = bA.id2UniqueEntityA("u1").create();
		Id2UniqueEntityA u2 = bA.id2UniqueEntityA("u2").create();
		Id2UniqueEntityA u3 = bA.id2UniqueEntityA("u3").create();

		bA.personA("p1").id2UniqueEntityA(u1).id2UniqueEntityAs(u1, u2).create();
		bA.personA("p2").id2UniqueEntityA(u2).id2UniqueEntityAs(u2, u3).create();
		bA.personA("p3").id2UniqueEntityA(u3).id2UniqueEntityAs(u1, u3).create();

		Id2UniqueEntity su1 = id2UniqueEntity(u1);

		// @formatter:off
		SelectQuery selectQuery = query()		
				.from(SmartPersonA.class, "p")
				.select("p", "nameA")
				.where()
					.property("p", "id2UniqueEntitySetA").contains().entity(su1)
				.done();
		// @formatter:on

		evaluate(selectQuery);

		assertResultContains("p1");
		assertResultContains("p3");
		assertNoMoreResults();
	}

}
