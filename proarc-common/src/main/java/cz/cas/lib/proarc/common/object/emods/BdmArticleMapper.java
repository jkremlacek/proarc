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
package cz.cas.lib.proarc.common.object.emods;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cas.lib.proarc.common.mods.custom.ModsConstants;
import cz.cas.lib.proarc.common.mods.ndk.NdkArticleMapper;
import cz.cas.lib.proarc.common.object.ndk.NdkMetadataHandler.ModsWrapper;
import cz.cas.lib.proarc.mods.GenreDefinition;
import cz.cas.lib.proarc.mods.ModsDefinition;
import cz.cas.lib.proarc.mods.StringPlusLanguagePlusAuthority;
import java.io.IOException;

/**
 * Maps born digital articles.
 *
 * @author Jan Pokorsky
 */
public class BdmArticleMapper extends NdkArticleMapper {

    /** {@code mods/genre/text()='article'}. */
    public static final String GENRE_ARTICLE_VALUE = "article";
    /** {@code mods/genre/@type='peer-reviewed'}. */
    public static final String GENRE_PEER_REVIEWED_TYPE = "peer-reviewed";

    @Override
    public ModsWrapper toJsonObject(ModsDefinition mods, Context ctx) {
        BdmModsWrapper mw = new BdmModsWrapper();
        mw.setMods(mods);
        GenreDefinition rGenre = null;
        for (GenreDefinition genre : mods.getGenre()) {
            if (GENRE_ARTICLE_VALUE.equals(genre.getValue()) && GENRE_PEER_REVIEWED_TYPE.equals(genre.getType())) {
                rGenre = genre;
                break;
            }
        }
        if (rGenre != null) {
            mods.getGenre().remove(rGenre);
            mw.setReviewed(Boolean.TRUE);
        } else {
            mw.setReviewed(Boolean.FALSE);
        }
        if (mods.getRecordInfo().isEmpty() || mods.getRecordInfo().get(0).getDescriptionStandard().isEmpty()){
            return mw;
        } else {
           String descriptionStandard = mods.getRecordInfo().get(0).getDescriptionStandard().get(0).getValue();
           if (descriptionStandard.equals(ModsConstants.VALUE_DESCRIPTIONSTANDARD_RDA)) {
               mw.setRdaRules(true);
           } else {
               mw.setRdaRules(false);
           }
            mods.getRecordInfo().get(0).getDescriptionStandard().clear();
           return mw;
        }
    }

    @Override
    public ModsDefinition fromJsonObject(ObjectMapper jsMapper, String json, Context ctx) throws IOException {
        BdmModsWrapper wrapper = jsMapper.readValue(json, BdmModsWrapper.class);
        ModsDefinition mods = wrapper.getMods();
        if (wrapper.getReviewed() != null && wrapper.getReviewed()) {
            GenreDefinition reviewed = new GenreDefinition();
            reviewed.setValue(GENRE_ARTICLE_VALUE);
            reviewed.setType(GENRE_PEER_REVIEWED_TYPE);
            mods.getGenre().add(0, reviewed);
        }
        StringPlusLanguagePlusAuthority descriptionStandard = new StringPlusLanguagePlusAuthority();
        if (wrapper.getRdaRules() != null && wrapper.getRdaRules()) {
            descriptionStandard.setValue(ModsConstants.VALUE_DESCRIPTIONSTANDARD_RDA);
        } else {
            descriptionStandard.setValue(ModsConstants.VALUE_DESCRIPTIONSTANDARD_AACR);
        }
        mods.getRecordInfo().get(0).getDescriptionStandard().add(0, descriptionStandard);
        return mods;
    }

    public static class BdmModsWrapper extends ModsWrapper {

        /**
         * A reviewed article. {@code mods/genre[@type'peer-reviewed' and text()='article'] }
         */
        private Boolean reviewed;
        private Boolean rdaRules;

        public Boolean getRdaRules() {
            return rdaRules;
        }

        public void setRdaRules(Boolean rdaRules) {
            this.rdaRules = rdaRules;
        }

        public Boolean getReviewed() {
            return reviewed;
        }

        public void setReviewed(Boolean reviewed) {
            this.reviewed = reviewed;
        }
    }

}
