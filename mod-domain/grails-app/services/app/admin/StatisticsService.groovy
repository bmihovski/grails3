package app.admin

import app.admin.jobsboard.Job
import app.admin.jobsboard.Publisher
import grails.transaction.Transactional

@Transactional
class StatisticsService {
    
    def getTopPublishers() {
        Job.list().countBy { it.publisher }
    }

}
