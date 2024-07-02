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

import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;

/**
 * This is similar to {@link KeyPropertyAssignment}, but the relationship is inverted. So for example if in the
 * delegates "A" has property which is a key on entity "B", then with this mapping we achieve that "SmartB" has a
 * property of type "SmartA" (or a set of "SmartA"). See examples below.
 * 
 * <p>
 * Example1: {@code
 * 
 * Folder 
 *  - Long id
 *  - Long parentId
 *  
 *  
 * SmartFolder
 *  - Set<SmartFolder> subFolders
 * 
 *  MD for SmartFolder.subFolders
 *  	qualfiedProperty -> Folder.parentId
 *  	keyProperty -> SmartFolder.id
 * }
 * 
 * <p>
 * 
 * Example 2: {@code
 * 
 *  Book
 *  - Long id
 *  - String isbn
 *  
 *  Person
 *  - Long id
 *  - String favouriteBookIsbn
 *  
 *  --------------------------------------------------------------
 *  
 *  SmartBook
 *  - Long id
 *  - Set<SmartPerson> bookFavourizers
 *  
 *  SmartPerson
 *  - Long id
 *  - String favouriteBookIsbn
 *  
 *  --------------------------------------------------------------
 *  
 *  SmartBook.bookFavourizers mapped with InverseKeyPropertyAssignment
 *  	property -> Person.favouriteBookIsbn
 *  	keyProperty -> Book.isbn
 *
 * }
 * 
 * @see KeyPropertyAssignment
 */
public interface InverseKeyPropertyAssignment extends KeyPropertyAssignment {

	EntityType<InverseKeyPropertyAssignment> T = EntityTypes.T(InverseKeyPropertyAssignment.class);

}
