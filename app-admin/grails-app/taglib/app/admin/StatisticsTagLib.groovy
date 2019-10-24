package app.admin

import app.admin.jobsboard.Tag
import app.admin.jobsboard.Type
import app.admin.jobsboard.Publisher


class StatisticsTagLib {
    static defaultEncodeAs = [taglib:'raw']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    def statisticsService
    static namespace = 's'
    
    def top = { attr, body ->
        def type = attr.type
        def result = []
        def counter = 0
        switch(type) {
            case "tags":
                result = statisticsService.getTopTags()
                counter = Tag.count()
                break
            case "types":
                result = statisticsService.getTopTypes()
                counter = Type.count()
                break
            case "publishers":
                result = statisticsService.getTopPublishers()
                counter = Publisher.count()
                break
            default:
                result = []
                break
        }
        if(result?.size()) {
            out << "<strong>${type.capitalize()}(${counter})</strong> <ul>" + result.each \
            {"<li>" + it + "</li>" } + "</ul>"
        } else {
            out << ""
        }
    }
}
