package app.admin

import app.admin.jobsboard.Job
import app.admin.jobsboard.Publisher
import app.admin.jobsboard.Tag
import app.admin.jobsboard.Type
import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification
import static org.assertj.core.api.Assertions.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(StatisticsService)
@Mock([Job, Tag])
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
            assertThat(publishers.size()).isEqualTo(0) 
    }
    
    void "get top publishers when we have multiple jobs published by the same publisher"() {
        given: "when we hane one 2 jobs published by the same publisher"
            def tag = Tag.build()
            def type = Type.build()
            def publisher = Publisher.build()
            Job.build(publisher: publisher, type: type, tags: [tag])
            Job.build(publisher: publisher, type: type, tags: [tag])
            
        when: "we get top publishers"
            def publishers = service.getTopPublishers()
            def pair = publishers.find{ key, value -> key.name.equals(publisher.name) }
            
        then: "we will see 2 publishers"
            assertThat(publishers.size()).isEqualTo(1)
            assertThat(pair?.value).isEqualTo(2)
    }
}
