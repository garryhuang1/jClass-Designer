package jcd;

/**
 * These are properties that are to be loaded from workspace_properties.xml. They
 * will provide custom labels and other UI details for this application and
 * it's custom workspace. Note that in this application we're using two different
 * properties XML files. simple_app_properties.xml is for settings known to the
 * Simple App Framework and so helps to set it up. These properties would be for
 * anything relevant to this custom application. The reason for loading this stuff
 * from an XML file like this is to make these settings independent of the code
 * and therefore easily interchangeable, like if we wished to change the language
 * the application ran in.
 * 
 * @author Richard McKenna
 * @author ?
 * @version 1.0
 */
public enum PropertyType {
    ADD_CLASS_ICON,
    ADD_INTERFACE_ICON,
    MINUS_ICON,
    PLUS_ICON,
    REDO_ICON,
    REMOVE_ICON,
    UNDO_ICON,
    ZOOM_IN_ICON,
    ZOOM_OUT_ICON,
    SELECT_ICON,
    RESIZE_ICON,
    
    ADD_CLASS_TOOLTIP,
    ADD_INTERFACE_TOOLTIP,
    MINUS_TOOLTIP,
    PLUS_TOOLTIP,
    REDO_TOOLTIP,
    REMOVE_TOOLTIP,
    UNDO_TOOLTIP,
    ZOOM_IN_TOOLTIP,
    ZOOM_OUT_TOOLTIP,
    SELECT_TOOLTIP,
    RESIZE_TOOLTIP
}