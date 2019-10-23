package app.admin

import app.admin.jobsboard.Job
import app.admin.jobsboard.Publisher
import app.admin.jobsboard.Tag
import app.admin.jobsboard.Type
import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(StatisticsService)
@Build([Job, Tag, Type, Publisher])
class StatisticsServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "get top publishers when we don't have nothing in our system"() {
        given: "when we don't have any job published"

        when: "we get top publishers"
            def publishers = service.getTopPublishers()
        then: "we will see 0 publishers"
            publishers.size() == 0
    }
}
