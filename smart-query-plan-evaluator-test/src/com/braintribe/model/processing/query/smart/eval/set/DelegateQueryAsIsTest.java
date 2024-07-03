// ============================================================================
// Copyright BRAINTRIBE TECHNOLOGY GMBH, Austria, 2002-2022
//
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
package com.braintribe.model.processing.query.smart.eval.set;

import static com.braintribe.utils.lcd.CollectionTools2.newList;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.braintribe.model.processing.query.smart.eval.set.base.AbstractSmartEvalTupleSetTests;
import com.braintribe.model.processing.query.smart.test.model.accessA.PersonA;
import com.braintribe.model.query.SelectQuery;
import com.braintribe.model.smartqueryplan.set.DelegateQueryAsIs;

public class DelegateQueryAsIsTest extends AbstractSmartEvalTupleSetTests {

	private final List<PersonA> persons = newList();

	@Before
	public void buildData() {
		for (int i = 0; i < 10; i++)
			persons.add(bA.personA("p" + i).companyNameA("c" + i).create());
	}

	@Test
	public void simpleDqs() {
		// @formatter:off
		SelectQuery query = query()
				.select("p", "id")
				.select("p", "nameA")
				.select("p", "companyNameA")
				.from(PersonA.T, "p")
				.done();
		// @formatter:on

		DelegateQueryAsIs dqs = builder.delegateQueryAsIs(setup.accessA, query);

		evaluate(dqs);

		assertContainsAllPersonData();
	}

	private void assertContainsAllPersonData() {
		for (PersonA p : persons)
			assertContainsTuple(p.getId(), p.getNameA(), p.getCompanyNameA());

		assertNoMoreTuples();
	}

}
