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

import com.braintribe.model.processing.query.smart.test.model.accessB.ItemB;
import com.braintribe.model.processing.query.smart.test.model.smart.SmartPersonA;
import com.braintribe.model.query.SelectQuery;

/**
 * @author peter.gazdik
 */
public class EntitySelection_Dqj_Tests extends AbstractSmartQueryTests {

	/** See EntitySelection_Dqj_PlannerTests#simpleInverseKeyPropertyJoin() */
	@Test
	public void simpleInverseKeyPropertyJoin() {
		bA.personA("pA1").create();
		bA.personA("pA2").create();

		ItemB i1 = bB.item("i1").singleOwnerName("pA1").create();
		ItemB i2 = bB.item("i2").singleOwnerName("pA1").create();

		// @formatter:off
		SelectQuery selectQuery = query()		
				.from(SmartPersonA.class, "p")
				.select("p", "inverseKeyItem")
				.done();
		// @formatter:on

		evaluate(selectQuery);

		assertResultContains(smartItem(i1));
		assertResultContains(smartItem(i2));
	}

}
