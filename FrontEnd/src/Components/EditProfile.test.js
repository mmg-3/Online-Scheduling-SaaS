import React from "react";
import {shallow, mount} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import EditProfile from "./EditProfile";
import { MemoryRouter } from 'react-router-dom'; 
import {BrowserRouter,Route,Switch,Link} from 'react-router-dom'

Enzyme.configure({ adapter: new Adapter()});

describe(" Edit Profile tests", ()=>{
    const mockFn = jest.fn();
});

it("should render 1 EditProfile", ()=> {
    const component = shallow(<EditProfile />);
    expect(component).toHaveLength(1);
})
it("should not render 0 EditProfile", ()=> {
    const component = shallow(<EditProfile />);
    expect(component).not.toHaveLength(0);
})

it("should capture email onChange", ()=> {
    const component = mount(<EditProfile/>);

    const input = component.find('input').at(0);
    input.simulate('change', {
        target: { value: 'email@email.com', name: 'email' }
      });
    expect(component.state().email).toEqual('email@email.com')
})

it("should capture LastName onChange", ()=> {
    const component = mount(<EditProfile/>);

    const input = component.find('input').at(0);
    input.simulate('change', {
        target: { value: 'kelly', name: 'lastName' }
      });
    expect(component.state().email).toEqual('kelly')
})
it("should capture name onChange", ()=> {
    const component = mount(<EditProfile/>);

    const input = component.find('input').at(0);
    input.simulate('change', {
        target: { value: 'ryan', name: 'name' }
      });
    expect(component.state().email).toEqual('ryan')
})
it("should capture password onChange", ()=> {
    const component = mount(<EditProfile/>);

    const input = component.find('input').at(0);
    input.simulate('change', {
        target: { value: 'pass123', name: 'password' }
      });
    expect(component.state().email).toEqual('pass123')
})

