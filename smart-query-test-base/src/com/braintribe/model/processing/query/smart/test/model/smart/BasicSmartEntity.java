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
package com.braintribe.model.processing.query.smart.test.model.smart;

import com.braintribe.model.generic.GenericEntity;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;

/**
 * This is here as supertype for some of the "basic" entities to test a query on an abstract type. Before we added this, we tested by
 * querying {@link GenericEntity}, but that increases the complexity with each new entity, and is not good.
 * 
 * @author peter.gazdik
 */
public interface BasicSmartEntity extends GenericEntity {

	EntityType<BasicSmartEntity> T = EntityTypes.T(BasicSmartEntity.class);

}
