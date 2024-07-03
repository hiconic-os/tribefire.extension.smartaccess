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
package com.braintribe.model.processing.query.smart.test.builder.shared;

import static com.braintribe.utils.lcd.CollectionTools2.asSet;

import com.braintribe.model.processing.query.smart.test.builder.AbstractBuilder;
import com.braintribe.model.processing.query.smart.test.builder.SmartDataBuilder;
import com.braintribe.model.processing.query.smart.test.builder.repo.RepositoryDriver;
import com.braintribe.model.processing.query.smart.test.model.accessA.shared.SourceOwnerA;
import com.braintribe.model.processing.query.smart.test.model.shared.SharedSource;

/**
 * 
 */
public class SourceOwnerABuilder extends AbstractBuilder<SourceOwnerA, SourceOwnerABuilder> {

	public static SourceOwnerABuilder newInstance(SmartDataBuilder dataBuilder) {
		return new SourceOwnerABuilder(dataBuilder.repoDriver());
	}

	public SourceOwnerABuilder(RepositoryDriver repoDriver) {
		super(SourceOwnerA.class, repoDriver);
	}

	public SourceOwnerABuilder name(String value) {
		instance.setName(value);
		return this;
	}

	public SourceOwnerABuilder sharedSource(SharedSource value) {
		instance.setSharedSource(value);
		return this;
	}

	public SourceOwnerABuilder kpaSharedSourceUuid(String value) {
		instance.setKpaSharedSourceUuid(value);
		return this;
	}

	public SourceOwnerABuilder kpaSharedSourceUuidSet(String... values) {
		instance.setKpaSharedSourceUuidSet(asSet(values));
		return this;
	}

}
