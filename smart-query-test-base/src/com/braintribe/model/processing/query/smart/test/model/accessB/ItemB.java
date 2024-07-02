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
package com.braintribe.model.processing.query.smart.test.model.accessB;

import java.util.Map;
import java.util.Set;

import com.braintribe.model.generic.i18n.LocalizedString;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;
import com.braintribe.model.processing.query.smart.test.model.smart.SmartItem;

/**
 * Mapped to {@link SmartItem}
 */

public interface ItemB extends StandardIdentifiableB {

	final EntityType<ItemB> T = EntityTypes.T(ItemB.class);

	// @formatter:off
	String getNameB();
	void setNameB(String nameB);

	LocalizedString getLocalizedNameB();
	void setLocalizedNameB(LocalizedString localizedNameB);

	PersonB getOwnerB();
	void setOwnerB(PersonB ownerB);

	ItemTypeB getItemType();
	void setItemType(ItemTypeB itemType);

	Set<ItemTypeB> getItemTypes();
	void setItemTypes(Set<ItemTypeB> itemTypes);

	Map<ItemTypeB, Integer> getItemTypeWeights();
	void setItemTypeWeights(Map<ItemTypeB, Integer> itemTypeWeights);

	// used for SmartPersonA.inverseKeyItem
	String getSingleOwnerName();
	void setSingleOwnerName(String singleOwnerName);

	// used for SmartPersonA.inverseKeyItemSet
	String getMultiOwnerName();
	void setMultiOwnerName(String multiOwnerName);

	// used for SmartPersonA.inverseKeySharedItem
	Set<String> getSharedOwnerNames();
	void setSharedOwnerNames(Set<String> sharedOwnerNames);

	// used for SmartPersonA.inverseKeyMultiSharedItemSet
	Set<String> getMultiSharedOwnerNames();
	void setMultiSharedOwnerNames(Set<String> multiSharedOwnerNames);
	// @formatter:on

}
