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
package com.braintribe.model.accessdeployment.smart.meta;

import com.braintribe.model.accessdeployment.IncrementalAccess;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;
import com.braintribe.model.meta.GmEntityType;
import com.braintribe.model.meta.GmProperty;
import com.braintribe.model.meta.data.QualifiedProperty;

/**
 * Example 2: {@code
 * 
 *  Person
 *  - String name
 *  
 *  Company
 *  - String name
 *  
 *  --------------------------------------------------------------
 *  
 *  PersonCompany
 *  - String personName
 *  - String companyName
 *  
 *  --------------------------------------------------------------
 *  
 *  SmartCompany
 *  - name
 *  
 *  SmartPerson
 *  - name
 *  - SmartCompany company
 *  
 *  
 *  --------------------------------------------------------------
 *  
 *  SmartPerson.company mapped with LinkPropertyAssignment:
 *  	linkAccess -> (access which contains PersonCompany instances)
 *  	key -> Person.name
 *  	linkKey -> PersonCompany.personName
 *  	otherKey -> Company.name
 *  	linkOtherKey -> PersonCompany.companyName
 *  
 *  }
 * 
 * @author peter.gazdik
 */
public interface LinkPropertyAssignment extends PropertyAssignment {

	EntityType<LinkPropertyAssignment> T = EntityTypes.T(LinkPropertyAssignment.class);

	// @formatter:off
	// PersonCompanyAccess
	IncrementalAccess getLinkAccess();
	void setLinkAccess(IncrementalAccess linkAccess);

	// Person.name
	QualifiedProperty getKey();
	void setKey(QualifiedProperty key);

	// Company.name
	QualifiedProperty getOtherKey();
	void setOtherKey(QualifiedProperty  otherKey);

	// PersonCompany
	GmEntityType  getLinkEntityType();
	void setLinkEntityType(GmEntityType  linkEntityType);
	
	// PersonCompany.personName
	GmProperty getLinkKey();
	void setLinkKey(GmProperty linkKey);

	// PersonCompany.companyName
	GmProperty getLinkOtherKey();
	void setLinkOtherKey(GmProperty linkOtherKey);
	// @formatter:on

}
