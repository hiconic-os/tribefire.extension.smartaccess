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
package com.braintribe.model.processing.query.smart.test.builder.constant;

import com.braintribe.model.processing.query.smart.test.builder.SmartDataBuilder;
import com.braintribe.model.processing.query.smart.test.builder.repo.RepositoryDriver;
import com.braintribe.model.processing.query.smart.test.model.accessA.constant.ConstantPropEntityA;

/**
 * 
 */
public class ConstantPropEntityABuilder extends AbstractConstantPropEntityABuilder<ConstantPropEntityA, ConstantPropEntityABuilder> {

	public static ConstantPropEntityABuilder newInstance(SmartDataBuilder dataBuilder) {
		return new ConstantPropEntityABuilder(dataBuilder.repoDriver());
	}

	public ConstantPropEntityABuilder(RepositoryDriver repoDriver) {
		super(ConstantPropEntityA.class, repoDriver);
	}

}
