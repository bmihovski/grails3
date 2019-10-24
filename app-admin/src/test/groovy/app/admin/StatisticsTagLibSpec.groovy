package app.admin

import grails.test.mixin.TestFor
import spock.lang.Specification

import app.admin.jobsboard.Job
import app.admin.jobsboard.Publisher
import app.admin.jobsboard.Tag
import app.admin.jobsboard.Type
import grails.buildtestdata.mixin.Build
import grails.test.mixin.Mock

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(StatisticsTagLib)
@Mock([Job, Tag])
@Build([Job, Publisher, Tag, Type])
class StatisticsTagLibSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "top for no publishers"() {
        expect:
            applyTemplate('<s:top type="publishers" />') == ""
    }
    
    void "top for multiple publishers, tags and types"() {
        given:
            def tag = Tag.build()
            def type = Type.build()
            def publisher = Publisher.build()
            
            Job.build(publisher: publisher, type: type, tags: [tag])
            Job.build(publisher: publisher, type: type, tags: [tag])
        expect:
            applyTemplate('<s:top type="publishers" />') == "<strong>Publishers</strong><ul>[name:2]</ul>"
            applyTemplate('<s:top type="types" />') == "<strong>Types</strong><ul>[name:2]</ul>"
            applyTemplate('<s:top type="tags" />') == "<strong>Tags</strong><ul>[[name:2]]</ul>"
    }
}
