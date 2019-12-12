import React, { Component } from 'react'
import IdleTimer from 'react-idle-timer'

export default class YourApp extends Component {
  constructor(props) {
    super(props)
    this.idleTimer = null
    this.onAction = this._onAction.bind(this)
    this.onActive = this._onActive.bind(this)
    this.onIdle = this._onIdle.bind(this)
  }

  render() {
    return (
      <div>
        <IdleTimer
          ref={ref => { this.idleTimer = ref }}
          element={document}
		  onActive={this.onActive}
          onIdle={this.onIdle}
          onAction={this.onAction}
		  debounce={250}
          timeout={1000 * 60 * 1} />
		{/* your app here */}
      </div>
    )
  }

  _onAction(e) {
    console.log('user did something', e)
  }

  _onActive(e) {
    console.log('user is active', e)
    console.log('time remaining', this.idleTimer.getRemainingTime())
  }

  _onIdle(e) {
    console.log('user is idle', e)
    console.log('last active', this.idleTimer.getLastActiveTime())
  }
}