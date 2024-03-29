package org.automation.angular.extras.find;

import org.automation.angular.WebDriverNG;
import org.automation.angular.WebElementNG;
import org.hamcrest.Factory;

import java.util.Collection;

import static org.automation.angular.extras.match.MatchersNG.*;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by shantonu on 9/2/16.
 * todo =. error exsists.
 */
public class InputFinderNG extends HtmlTagFinderNG {
    @Override
    protected String tagDescription() {
        return "input field";
    }

    @Override
    protected String tagName() {
        return "input";
    }

    @Factory
    public static HtmlTagFinderNG textbox() {
        return new InputFinderNG().with(attribute("type", equalTo("text")));
    }

    @Factory
    public static HtmlTagFinderNG imageButton() {
        return new InputFinderNG().with(attribute("type", equalTo("image")));
    }

    @Factory
    public static HtmlTagFinderNG imageButton(String label) {
        return imageButton().with(value((label)));
    }

    @Factory
    public static HtmlTagFinderNG radioButton() {
        return new InputFinderNG().with(attribute("type", equalTo("radio")));
    }

    @Factory
    public static HtmlTagFinderNG radioButton(String id) {
        return radioButton().with(attribute("id", equalTo(id)));
    }

    @Factory
    public static HtmlTagFinderNG submitButton() {
        return new InputFinderNG().with(attribute("type", equalTo("submit")));
    }

    @Factory
    public static HtmlTagFinderNG submitButton(String label) {
        return submitButton().with(value(label));
    }

    @Override
    protected Collection<WebElementNG> extractFrom(WebDriverNG webDriverNG) {
        return null;
    }
}
