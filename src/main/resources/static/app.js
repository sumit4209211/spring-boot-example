import {Component, View, NgFor} from 'angular2/angular2';
import {Http, Response} from 'angular2/http'
@Component({
    selector: 'http'
})
@View({
    templateUrl: 'people.html',
    directives: [NgFor]
})
export class HttpSample {
    result: Object;
    constructor(http: Http) {
        this.result = {friends:[]};
        http.get('/accounts/1').map((res: Response) => res.json()).subscribe(res => this.result = res);
    }
}