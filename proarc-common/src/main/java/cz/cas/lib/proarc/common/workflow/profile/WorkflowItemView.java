/*
 * Copyright (C) 2015 Jan Pokorsky
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package cz.cas.lib.proarc.common.workflow.profile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Jan Pokorsky
 */
@XmlAccessorType(XmlAccessType.NONE)
public class WorkflowItemView {

    private final DisplayableType<?> item;
    protected final String lang;

    public WorkflowItemView(DisplayableType<?> item, String lang) {
        this.item = item;
        this.lang = lang;
    }

    @XmlElement(name = WorkflowProfileConsts.NAME)
    public String getName() {
        return item.getName();
    }

    @XmlElement(name = WorkflowProfileConsts.TITLE_EL)
    public String getTitle() {
        return item.getTitle(lang, getName());
    }

    @XmlElement(name = WorkflowProfileConsts.HINT_EL)
    public String getHint() {
        return item.getHint(lang, null);
    }

    @XmlElement(name = WorkflowProfileConsts.DISABLED)
    public boolean isDisabled() {
        return item.isDisabled();
    }


}
