package app.admin

import app.admin.jobsboard.Job
import app.admin.jobsboard.Publisher
import app.admin.jobsboard.Type
import app.admin.jobsboard.Tag
import grails.gorm.transactions.Transactional

@Transactional
class StatisticsService {
    
    def getTopPublishers() {
        Job.list().countBy { it.publisher }
    }

    def getTopTags() {
        Job.list().countBy { it.tags }
    }
    
    def getTopTypes() {
        Job.list().countBy { it.type }
    }
    
}
