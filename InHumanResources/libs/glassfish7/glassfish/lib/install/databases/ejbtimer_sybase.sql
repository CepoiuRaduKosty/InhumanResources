/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

CREATE TABLE EJB__TIMER__TBL (
CREATIONTIMERAW      NUMERIC(20,0)  NOT NULL,
BLOB                 IMAGE          NULL,
TIMERID              VARCHAR(255)   NOT NULL,
CONTAINERID          NUMERIC(20,0)  NOT NULL,
OWNERID              VARCHAR(255)   NULL,
STATE                INTEGER        NOT NULL,
PKHASHCODE           INTEGER        NOT NULL,
INTERVALDURATION     NUMERIC(20,0)  NOT NULL,
INITIALEXPIRATIONRAW NUMERIC(20,0)  NOT NULL,
LASTEXPIRATIONRAW    NUMERIC(20,0)  NOT NULL,
SCHEDULE             VARCHAR(255)   NULL,
APPLICATIONID        NUMERIC(20,0)  NOT NULL,
CONSTRAINT PK_EJB__TIMER__TBL PRIMARY KEY (TIMERID)
)
