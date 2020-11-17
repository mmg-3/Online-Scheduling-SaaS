import axios from 'axios'

class DataService {
    retrieve(){

    return axios.get('http://localhost:8080/api/person/23bb')
}
}
export default new DataService()