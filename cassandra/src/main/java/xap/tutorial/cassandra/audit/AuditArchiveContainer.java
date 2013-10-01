package xap.tutorial.cassandra.audit;

import org.openspaces.archive.Archive;
import org.openspaces.events.DynamicEventTemplate;

import com.gigaspaces.document.SpaceDocument;
import com.j_spaces.core.client.SQLQuery;

@Archive(batchSize = 100)
public class AuditArchiveContainer {

	@DynamicEventTemplate
    public SQLQuery<SpaceDocument> getTemplate() {
        final SQLQuery<SpaceDocument> dynamicTemplate = 
            new SQLQuery<SpaceDocument>("AuditRecord", "");
        return dynamicTemplate;
    }
}