package org.plweb.desktop

import org.eclipse.swt.*
import org.eclipse.swt.layout.*
import org.eclipse.swt.widgets.*
import org.eclipse.swt.browser.*

class AppLoader {
	static void main(String[] args) {
		Display display = new Display()
		final Shell shell = new Shell(display)
		shell.setSize(280, 300)
		shell.setText("PLWeb Desktop")

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		shell.setLayout(gridLayout)

		final Browser browser = new Browser(shell, SWT.NONE)
		def data = new GridData()
		data.horizontalAlignment = GridData.FILL;
		data.verticalAlignment = GridData.FILL;
		data.horizontalSpan = 3;
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		browser.setLayoutData(data);

		browser.addLocationListener(new LocationListener() {
			public void changed(LocationEvent event) {
				println event.location
			}
			public void changing(LocationEvent event) {
				println event.location

				if (event.location.startsWith('edit')) {
					println "edit"
					println browser.evaluate('return editor.getValue();').toString()
					'mvim /Users/lyhcode/project/test/test.java'.execute()
				}
				if (event.location.startsWith('test')) {
					println "edit"
					'open /Users/lyhcode/project/fetnet-sms/mpush/build/reports/tests/index.html'.execute()
				}
			}
		});

		shell.open()	
		browser.url = 'http://cloud.plweb.org/'

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}
