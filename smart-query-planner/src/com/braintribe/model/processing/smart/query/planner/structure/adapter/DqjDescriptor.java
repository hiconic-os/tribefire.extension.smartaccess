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
package com.braintribe.model.processing.smart.query.planner.structure.adapter;

import java.util.List;

/**
 * 
 */
public interface DqjDescriptor {

	/**
	 * Returns list of joined properties for given DQJ. Joined entity means from smart entity point of view.
	 */
	List<String> getJoinedEntityDelegatePropertyNames();

	/**
	 * Returns owner property for given joined property. Relation owner means from smart entity point of view.
	 */
	String getRelationOwnerDelegatePropertyName(String joinedEntityDelegatePropertyName);

	ConversionWrapper getRelationOwnerPropertyConversion(String joinedEntityDelegatePropertyName);
	
	ConversionWrapper getJoinedEntityPropertyConversion(String joinedEntityDelegatePropertyName);
	
	boolean getForceExternalJoin();
	
}
