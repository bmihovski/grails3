package app.admin.jobsboard

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.lang.Subject

class TagSpec extends Specification implements DomainUnitTest<Tag> {
    
    @Subject
    Tag domain

    def setup() {
    }

    def cleanup() {
    }

    void "completed tag creation"() {
        given: "a completely new tag"
            domain = new Tag(name: "Remote")
        expect: "we can save a complete job tag"
            domain.validate(['name'])
    }
    
    void "can't save tag without a name"() {
        given: "a job tag without a name"
            domain = new Tag(name: "")
        expect: "we can't save the tag"
            domain.validate(['name'])
    }
}
