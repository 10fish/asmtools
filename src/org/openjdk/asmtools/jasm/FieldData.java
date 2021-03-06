/*
 * Copyright (c) 1996, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package org.openjdk.asmtools.jasm;

import org.openjdk.asmtools.jasm.Tables.AttrTag;
import java.io.IOException;

/**
 *
 */
class FieldData extends MemberData {

    /*-------------------------------------------------------- */
    /* FieldData Fields */
    protected ConstantPool.ConstValue_Pair nape;
    private AttrData initValue;
    /*-------------------------------------------------------- */

    public FieldData(ClassData cls, int acc, ConstantPool.ConstValue_Pair nape) {
        super(cls, acc);
        this.nape = nape;
        if (Modifiers.hasPseudoMod(acc)) {
            createPseudoMod();
        }
    }

    public void SetValue(Argument value_cpx) {
        initValue = new CPXAttr(cls, AttrTag.ATT_ConstantValue.parsekey(),
                value_cpx);
    }

    public void write(CheckedDataOutputStream out) throws IOException, Parser.CompilerError {
        out.writeShort(access);
        out.writeShort(nape.left.arg);
        out.writeShort(nape.right.arg);

        DataVector attrs = new DataVector();
        if (initValue != null) {
            attrs.add(initValue);
        }
        if (syntheticAttr != null) {
            attrs.add(syntheticAttr);
        }
        if (deprecatedAttr != null) {
            attrs.add(deprecatedAttr);
        }
        if (annotAttrVis != null) {
            attrs.add(annotAttrVis);
        }
        if (annotAttrInv != null) {
            attrs.add(annotAttrInv);
        }
        if (type_annotAttrVis != null) {
            attrs.add(type_annotAttrVis);
        }
        if (type_annotAttrInv != null) {
            attrs.add(type_annotAttrInv);
        }
        attrs.write(out);
    }
} // end FieldData

