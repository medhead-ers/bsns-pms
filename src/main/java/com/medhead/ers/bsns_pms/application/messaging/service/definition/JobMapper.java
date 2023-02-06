package com.medhead.ers.bsns_pms.application.messaging.service.definition;

import com.medhead.ers.bsns_pms.application.messaging.event.Event;
import com.medhead.ers.bsns_pms.application.messaging.job.Job;

public interface JobMapper {
    Job createJobFromEvent(Event event) throws Exception;
}
