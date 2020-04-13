/*
 * Copyright (C) 2020 Lukas Sykora
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package cz.cas.lib.proarc.webapp.client.action.administration;

import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import cz.cas.lib.proarc.webapp.client.ClientMessages;
import cz.cas.lib.proarc.webapp.client.action.AbstractAction;
import cz.cas.lib.proarc.webapp.client.action.ActionEvent;
import cz.cas.lib.proarc.webapp.client.action.Actions;
import cz.cas.lib.proarc.webapp.client.action.DeleteAction;
import cz.cas.lib.proarc.webapp.client.ds.RestConfig;
import cz.cas.lib.proarc.webapp.client.ds.UpdateAllObjectsDataSource;
import cz.cas.lib.proarc.webapp.client.widget.StatusView;
import java.util.logging.Logger;

/**
 * @author Lukas Sykora
 */
public final class UpdateAllObjectsAction extends AbstractAction {

    private static final Logger LOG = Logger.getLogger(DeleteAction.class.getName());

    private final ClientMessages i18n;

    public UpdateAllObjectsAction(ClientMessages i18n) {
        super(i18n.DigitalObjectUpdateAllObjectsAction_Title(), null, i18n.DigitalObjectUpdateAllObjectsAction_FinishMessage());
        this.i18n = i18n;
    }

    @Override
    public boolean accept(ActionEvent event) {
        return true;
    }

    @Override
    public void performAction(ActionEvent event) {
        Record[] records = Actions.getSelection(event);
        Record localRecord = null;
        for (Record record : records) {
            localRecord = record;
        }
        updateAll(localRecord);
    }

    private void updateAll(Record record) {
        DSRequest dsRequest = new DSRequest();
        dsRequest.setHttpMethod("POST");
        UpdateAllObjectsDataSource ds = UpdateAllObjectsDataSource.getInstance();
        ds.addData(record, new DSCallback() {
            @Override
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                if (RestConfig.isStatusOk(response)) {
                    StatusView.getInstance().show(i18n.DigitalObjectUpdateAllObjectsAction_FinishMessage());
                }
            }
        }, dsRequest);
    }
}
