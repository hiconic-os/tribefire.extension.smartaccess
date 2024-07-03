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
package com.braintribe.model.access.smart;

import com.braintribe.model.accessdeployment.smart.meta.PropertyAssignment;
import com.braintribe.model.accessdeployment.smart.meta.SmartUnmapped;
import com.braintribe.model.generic.GenericEntity;
import com.braintribe.model.generic.reflection.Property;
import com.braintribe.model.processing.session.impl.persistence.AbstractPersistenceGmSession;
import com.braintribe.model.processing.session.impl.persistence.EagerLoader;
import com.braintribe.model.processing.smart.query.planner.structure.ModelExpert;

/**
 * LazyLoader used by {@link SmartAccess} when traversing query result according to given traversing criteria.
 * 
 * @author peter.gazdik
 */
public class SmartEagerLoader extends EagerLoader {

	private final ModelExpert modelExpert;

	public SmartEagerLoader(SmartAccess access, AbstractPersistenceGmSession session, ModelExpert modelExpert) {
		super(access, session);

		this.modelExpert = modelExpert;
	}

	@Override
	protected void eagerlyLoad(GenericEntity entity, Property property) {
		if (isPropertyMapped(entity, property))
			super.eagerlyLoad(entity, property);
		else
			property.setDirect(entity, property.getDefaultRawValue());
	}

	@Override
	protected boolean isCandidateForEagerLoader(GenericEntity owner, Property property) {
		return super.isCandidateForEagerLoader(owner, property) && isPropertyMapped(owner, property);
	}

	private boolean isPropertyMapped(GenericEntity owner, Property property) {
		PropertyAssignment md = getPropertyMappingMd(owner, property);
		return md != null && md.type() != SmartUnmapped.T;
	}

	private PropertyAssignment getPropertyMappingMd(GenericEntity owner, Property property) {
		String partition = owner.getPartition();

		// IncrementalAccess access = modelExpert.getAccess(partition);

		// modelExpert.resolveProp

		// TODO figuring out the use-case might be more complicated - in case the underlying access doesn't set
		// partition directly to accessId
		return modelExpert.cmdResolver.getMetaData() //
				.entity(owner) //
				.property(property) //
				.useCase(partition) //
				.meta(PropertyAssignment.T) //
				.exclusive();
	}

}
