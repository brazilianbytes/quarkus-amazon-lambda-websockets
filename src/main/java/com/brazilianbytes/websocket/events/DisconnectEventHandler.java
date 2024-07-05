package com.brazilianbytes.websocket.events;

import com.brazilianbytes.websocket.events.common.AbstractEventHandler;
import com.brazilianbytes.websocket.events.common.EventHandlerType;
import com.brazilianbytes.websocket.events.common.EventType;

@EventHandlerType(EventType.DISCONNECT)
public class DisconnectEventHandler extends AbstractEventHandler {
}
