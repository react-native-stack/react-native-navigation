import React, {Component} from 'react';
import {
  Text,
  View,
} from 'react-native';

export default class InAppNotification extends Component {

  constructor(props) {
    super(props);
    this._counter = 0;
  }

  render() {
    this._counter++;
    const message = `I'm notification #${this._counter}!`;
    return (
      <View style={{flex: 1, flexDirection: 'row', backgroundColor: 'white', alignItems: 'center', justifyContent: 'center'}}>
        <Text style={{color: 'black', fontSize: 20}}>{message}</Text>
      </View>
    );
  }
}
