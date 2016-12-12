
/**
 * CArtAgO - DEIS, University of Bologna
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package cartago.infrastructure.rmi.topology;

import cartago.infrastructure.topology.ICartagoTreeRemote;
import cartago.topology.WorkspaceTree;
import cartago.topology.TopologyException;
import cartago.CartagoNode;
import cartago.infrastructure.rmi.CartagoNodeRemote;
import cartago.infrastructure.CartagoInfrastructureLayerException;
import cartago.CartagoException;
import cartago.CartagoWorkspace;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import cartago.WorkspaceId;

public class CartagoTreeRemote extends UnicastRemoteObject implements ICartagoTreeRemote
{

    private String fullAddress;
    private WorkspaceTree tree;

    public CartagoTreeRemote(WorkspaceTree tree) throws Exception
    {
	this.tree = tree;
	
    }
    

    public String getNodeAddressFromPath(String path) throws TopologyException
    {
	return this.tree.getNodeAddressFromPath(path);
    }
    
    public void installTree(String address, int port) throws Exception
    {
	//the registry shoud be already created for the node
	fullAddress = address+":"+port;
	Naming.bind("rmi://"+fullAddress+"/tree", this);
    }
    
    public void mount(String wspPath, WorkspaceId wspId) throws TopologyException
    {
	this.tree.mount(wspPath, wspId);
    }

    public WorkspaceTree getTree()
    {
	return this.tree;
    }

}